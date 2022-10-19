package cutoff.model;

import java.time.LocalDateTime;

public class CurrencyCutoffTime {
    private String base;
    private String dateTime;

    public CurrencyCutoffTime(){
        this("", LocalDateTime.now().toString());
    }

    public CurrencyCutoffTime(String base, String dateTime) {
        this.base = base;
        this.dateTime = dateTime;
    }

    public String getBase() {
        return base;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "CurrencyCutoffTime{" +
                "base='" + base + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
