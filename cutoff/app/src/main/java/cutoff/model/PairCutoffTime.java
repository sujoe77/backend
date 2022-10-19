package cutoff.model;

import java.time.LocalDateTime;

public class PairCutoffTime extends CurrencyCutoffTime {
    private final String term;

    public PairCutoffTime(String base, String term, LocalDateTime dateTime) {
        super(base, dateTime);
        this.term = term;
    }

    public String getTerm() {
        return term;
    }
}
