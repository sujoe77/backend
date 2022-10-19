package cutoff.service;

import cutoff.jdbc.CutoffTimeDao;
import cutoff.model.CurrencyCutoffTime;
import cutoff.model.PairCutoffTime;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cutoff.util.Util.*;

@Service
public class CutoffService {
    private static final Map<String, String> CUTOFF_TIME_CACHE = new ConcurrentHashMap<>();

    CutoffTimeDao cutoffTimeDao;

    static {
        CUTOFF_TIME_CACHE.put("EUR/USD_2022-12-01", "16:00");
        CUTOFF_TIME_CACHE.put("EUR/GBP_2022-12-01", "17:00");
        CUTOFF_TIME_CACHE.put("EUR/JPY_2022-12-02", "12:00");
    }

    public String getCutoffTime(String pair, String date) {
        String ret = CUTOFF_TIME_CACHE.get(pair + "_" + date);
        if (isEmpty(ret)) {
            ret = CUTOFF_TIME_CACHE.get(getInverse(pair) + "_" + date);
        }
        return isEmpty(ret) ? "Not available" : ret;
    }

    public void setCutoffTime(CurrencyCutoffTime currencyCutoffTime) {
        cutoffTimeDao.updateDB(currencyCutoffTime);
        List<PairCutoffTime> updated = cutoffTimeDao.getUpdated(currencyCutoffTime);
        updated.forEach(pt -> updateCache(pt));
    }

    private boolean updateCache(PairCutoffTime pairCutoffTime) {
        String key = getKey(pairCutoffTime.getBase(), pairCutoffTime.getTerm(), pairCutoffTime.getDateTime());
        LocalTime time = pairCutoffTime.getDateTime().toLocalTime();
        if (updateCache(key, time)) return true;
        key = getKey(pairCutoffTime.getTerm(), pairCutoffTime.getBase(), pairCutoffTime.getDateTime());
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
}
