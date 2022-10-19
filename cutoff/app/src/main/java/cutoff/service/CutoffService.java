package cutoff.service;

import cutoff.jdbc.JdbcClient;
import cutoff.jdbc.JdbcConfig;
import cutoff.model.CurrencyCutoffTime;
import cutoff.model.PairCutoffTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cutoff.util.Util.*;

@Service
public class CutoffService implements InitializingBean {
    private static final Map<String, String> CUTOFF_TIME_CACHE = new ConcurrentHashMap<>();

    private JdbcClient jdbcClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<PairCutoffTime> cached = getJdbcClient().getCachedForDate(List.of(
                LocalDate.of(2022, 10, 18),
                LocalDate.of(2022, 10, 19),
                LocalDate.of(2022, 10, 20)
        ));
        cached.forEach(ct -> updateCache(ct));
    }

    public String getCutoffTime(String pair, String date) throws SQLException {
        String ret = CUTOFF_TIME_CACHE.get(pair + "_" + date);
        if (isEmpty(ret)) {
            ret = CUTOFF_TIME_CACHE.get(getInverse(pair) + "_" + date);
        }
        if (isEmpty(ret)) {
            ret = getJdbcClient().getCutoffTime(pair.substring(0, 3), pair.substring(4), LocalDate.parse(date)).toString();
        }
        return isEmpty(ret) ? "Not available" : ret;
    }

    public void setCutoffTime(List<CurrencyCutoffTime> currencyCutoffTime) throws SQLException {
        List<PairCutoffTime> updated = getJdbcClient().updateDB(currencyCutoffTime);
        updated.forEach(pt -> updateCache(pt));
    }

    private boolean updateCache(PairCutoffTime pairCutoffTime) {
        String key = getKey(pairCutoffTime.getBase(), pairCutoffTime.getTerm(), LocalDateTime.parse(pairCutoffTime.getDateTime()));
        LocalTime time = LocalDateTime.parse(pairCutoffTime.getDateTime()).toLocalTime();
        if (updateCache(key, time)) return true;
        key = getKey(pairCutoffTime.getTerm(), pairCutoffTime.getBase(), LocalDateTime.parse(pairCutoffTime.getDateTime()));
        if (updateCache(key, time)) return true;
        return false;
    }

    private boolean updateCache(String key, LocalTime time) {
        String cachedTime = CUTOFF_TIME_CACHE.get(key);
        if (cachedTime == null || cachedTime.isEmpty() || LocalTime.parse(cachedTime).isAfter(time)) {
            CUTOFF_TIME_CACHE.put(key, time.toString());
            return true;
        }
        return false;
    }

    public JdbcClient getJdbcClient() {
        if (jdbcClient == null) {
            jdbcClient = new JdbcClient(new JdbcConfig(10, 1));
        }
        return jdbcClient;
    }
}
