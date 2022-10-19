package cutoff.util;

import java.time.LocalDateTime;

public class Util {
    public static String getInverse(String pair) {
        return pair.substring(4, 7) + "/" + pair.substring(0, 3);
    }

    public static String getKey(String base, String term, LocalDateTime dateTime) {
        String pair = base + "/" + term;
        return pair + "_" + dateTime.toLocalDate().toString();
    }
}
