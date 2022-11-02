package cutoff.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import cutoff.model.CurrencyCutoffTime;
import cutoff.model.PairCutoffTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcClient {
    private final JdbcConfig config;
    private DataSource dataSource;

    Logger log = LoggerFactory.getLogger(JdbcClient.class);

    public JdbcClient(JdbcConfig config) {
        this.config = config;
    }

    public String getCutoffTime(String base, String term, LocalDate date) throws SQLException {
        try (PreparedStatement stat = getConnection().prepareStatement(Constants.SQL_SELECT_CUTOFF_TIME)) {
            stat.setDate(1, Date.valueOf(date));
            stat.setString(2, base);
            stat.setString(3, term);
            stat.setString(4, term);
            stat.setString(5, base);
            ResultSet rs = stat.executeQuery();
            if (!rs.next()) {
                return "";
            }
            return rs.getTime(1).toLocalTime().toString();
        }
    }

    public List<PairCutoffTime> getCachedForDate(List<LocalDate> dates) throws SQLException {
        try (Connection connection = getConnection()) {
            List<PairCutoffTime> ret = new ArrayList<>();
            for (LocalDate date : dates) {
                ret.addAll(getCachedForDate(connection, date));
            }
            return ret;
        }
    }

    public List<PairCutoffTime> getCachedForDate(Connection connection, LocalDate date) throws SQLException {
        try (PreparedStatement stat = connection.prepareStatement(Constants.SQL_SELECT_ALL_CACHED_CUTOFF_TIME_FOR_DATE)) {
            List<PairCutoffTime> ret = new ArrayList<>();
            stat.setDate(1, Date.valueOf(date));
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                ret.add(new PairCutoffTime(
                        rs.getString("base"),
                        rs.getString("term"),
                        String.format("%sT%s", date.toString(), rs.getTime("cutoff_time").toString())
                ));
            }
            return ret;
        }
    }

    public List<PairCutoffTime> updateDB(List<CurrencyCutoffTime> currencyCutoffTimeList) throws SQLException {
        try (Connection connection = getConnection()) {
            List<PairCutoffTime> ret = new ArrayList<>();
            for (CurrencyCutoffTime cutoffTime : currencyCutoffTimeList) {
                log.info("Processing " + cutoffTime);
                addUpdates(connection, cutoffTime);
                LocalDate tradingDate = LocalDateTime.parse(cutoffTime.getDateTime()).toLocalDate();
                if (!isTradingDateInitialized(connection, tradingDate)) {
                    initTradingDate(connection, tradingDate);
                }
                updateCutoffTimes(connection, cutoffTime);
                ret.addAll(getUpdated(connection, cutoffTime));
            }
            return ret;
        }
    }

    public int addUpdates(Connection connection, CurrencyCutoffTime currencyCutoffTime) throws SQLException {
        try (PreparedStatement stat = connection.prepareStatement(Constants.SQL_INSERT_CURRENCY_CUTOFF_UPDATES)) {
            stat.setString(1, currencyCutoffTime.getBase());
            LocalDateTime dateTime = LocalDateTime.parse(currencyCutoffTime.getDateTime());
            stat.setDate(2, Date.valueOf(dateTime.toLocalDate()));
            stat.setTime(3, Time.valueOf(dateTime.toLocalTime()));
            stat.setString(4, "");
            int ret = stat.executeUpdate();
            connection.commit();
            return ret;
        }
    }

    public boolean isTradingDateInitialized(Connection connection, LocalDate date) throws SQLException {
        try (PreparedStatement stat = connection.prepareStatement(Constants.SQL_CHECK_TRADING_DATE_INITIALIZED)) {
            stat.setDate(1, Date.valueOf(date));
            ResultSet rs = stat.executeQuery();
            return rs.next();
        }
    }

    public int initTradingDate(Connection connection, LocalDate date) throws SQLException {
        try (PreparedStatement stat = connection.prepareStatement(Constants.SQL_INIT_TRADING_DATE)) {
            stat.setDate(1, Date.valueOf(date));
            int ret = stat.executeUpdate();
            connection.commit();
            return ret;
        }
    }

    public int updateCutoffTimes(Connection connection, CurrencyCutoffTime currencyCutoffTime) throws SQLException {
        try (PreparedStatement stat = connection.prepareStatement(Constants.SQL_UPDATE_CUTOFF)) {
            LocalDateTime dateTime = LocalDateTime.parse(currencyCutoffTime.getDateTime());
            stat.setTime(1, Time.valueOf(dateTime.toLocalTime()));
            stat.setString(2, currencyCutoffTime.getBase());
            stat.setString(3, currencyCutoffTime.getBase());
            stat.setDate(4, Date.valueOf(dateTime.toLocalDate()));
            stat.setTime(5, Time.valueOf(dateTime.toLocalTime()));
            int ret = stat.executeUpdate();
            connection.commit();
            return ret;
        }
    }

    public List<PairCutoffTime> getUpdated(Connection connection, CurrencyCutoffTime currencyCutoffTime) throws SQLException {
        try (PreparedStatement stat = connection.prepareStatement(Constants.SQL_SELECT_UPDATED)) {
            stat.setString(1, currencyCutoffTime.getBase());
            stat.setString(2, currencyCutoffTime.getBase());
            LocalDateTime dateTime = LocalDateTime.parse(currencyCutoffTime.getDateTime());
            stat.setDate(3, Date.valueOf(dateTime.toLocalDate()));
            stat.setTime(4, Time.valueOf(dateTime.toLocalTime()));
            ResultSet rs = stat.executeQuery();
            List<PairCutoffTime> ret = new ArrayList<>();
            while (rs.next()) {
                ret.add(new PairCutoffTime(
                        rs.getString("base"),
                        rs.getString("term"),
                        currencyCutoffTime.getDateTime()
                ));
            }
            return ret;
        }
    }

    public int insertPairConfig(String base, String term, boolean cached) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement stat = connection.prepareStatement(Constants.SQL_INSERT_PAIR_CONFIG)) {
            stat.setString(1, base);
            stat.setString(2, term);
            stat.setBoolean(3, cached);
            int ret = stat.executeUpdate();
            connection.commit();
            return ret;
        }
    }

    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    public DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = initDataSource(config);
        }
        return dataSource;
    }

    private DataSource initDataSource(JdbcConfig databaseConfig) {
//        String url = format(JdbcConfig.JDBC_URL,
//                databaseConfig.getInstance(),
//                databaseConfig.getDatabaseName(),
//                databaseConfig.getAuthenticationScheme(),
//                databaseConfig.getIntegratedSecurity(),
//                databaseConfig.getUser(),
//                databaseConfig.getPassword()
//        );
        String url = JdbcConfig.JDBC_URL;

        return new HikariDataSource(
                new HikariConfig() {{
                    setJdbcUrl(url);
                    setMaximumPoolSize(databaseConfig.getMaxPoolSize());
                    setMinimumIdle(databaseConfig.getMinIdlePoolSize());
                    setAutoCommit(false);
                }}
        );
    }
}
