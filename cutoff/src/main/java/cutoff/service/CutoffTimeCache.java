package cutoff.service;

import cutoff.jdbc.JdbcClient;
import cutoff.model.PairCutoffTime;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cutoff.util.Util.getKey;

public class CutoffTimeCache {
    public static final Map<String, String> CUTOFF_TIME_CACHE = new ConcurrentHashMap<>();
    public static final List<LocalDate> TRADING_DATES = List.of(
            LocalDate.of(2022, 10, 18),
            LocalDate.of(2022, 10, 19),
            LocalDate.of(2022, 10, 20)
    );

    public static void initCache(JdbcClient client) throws SQLException {
        List<PairCutoffTime> cached = client.getCachedForDate(TRADING_DATES);
        cached.forEach(ct -> updateCache(ct));
    }

    public static boolean updateCache(PairCutoffTime pairCutoffTime) {
        String base = pairCutoffTime.getBase();
        String term = pairCutoffTime.getTerm();
        LocalDateTime dateTime = LocalDateTime.parse(pairCutoffTime.getDateTime());
        String key = getKey(base, term, dateTime);
        LocalTime time = dateTime.toLocalTime();
        if (updateCache(key, time)) return true;
        key = getKey(term, base, dateTime);
        if (updateCache(key, time)) return true;
        return false;
    }

    public static boolean updateCache(String key, LocalTime time) {
        String cachedTime = CUTOFF_TIME_CACHE.get(key);
        if (cachedTime == null || cachedTime.isEmpty() || LocalTime.parse(cachedTime).isAfter(time)) {
            CUTOFF_TIME_CACHE.put(key, time.toString());
            return true;
        }
        return false;
    }
}
