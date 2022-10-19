package cutoff.service;

import cutoff.jdbc.JdbcClient;
import cutoff.jdbc.JdbcConfig;
import cutoff.model.CurrencyCutoffTime;
import cutoff.model.PairCutoffTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static cutoff.util.Util.*;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public class CutoffService implements InitializingBean {
    private JdbcClient jdbcClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        CutoffTimeCache.initCache(getJdbcClient());
    }

    public String getCutoffTime(String pair, String date) throws SQLException {
        String ret = CutoffTimeCache.CUTOFF_TIME_CACHE.get(pair + "_" + date);
        if (isEmpty(ret)) {
            ret = CutoffTimeCache.CUTOFF_TIME_CACHE.get(getInverse(pair) + "_" + date);
        }
        if (isEmpty(ret)) {
            ret = getJdbcClient().getCutoffTime(pair.substring(0, 3), pair.substring(4), LocalDate.parse(date)).toString();
        }
        return isEmpty(ret) ? "Not available" : ret;
    }

    public void setCutoffTime(List<CurrencyCutoffTime> currencyCutoffTime) throws SQLException {
        List<PairCutoffTime> updated = getJdbcClient().updateDB(currencyCutoffTime);
        updated.forEach(pt -> CutoffTimeCache.updateCache(pt));
    }

    public JdbcClient getJdbcClient() {
        if (jdbcClient == null) {
            jdbcClient = new JdbcClient(new JdbcConfig(10, 1));
        }
        return jdbcClient;
    }
}
