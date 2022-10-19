package cutoff.model;

import java.time.LocalDateTime;

public class CurrencyCutoffTime {
    private final String base;
    private final LocalDateTime dateTime;

    public CurrencyCutoffTime(String base, LocalDateTime dateTime) {
        this.base = base;
        this.dateTime = dateTime;
    }

    public String getBase() {
        return base;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
