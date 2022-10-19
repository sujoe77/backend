package cutoff.model;

import java.time.LocalDateTime;

public class PairCutoffTime extends CurrencyCutoffTime {
    private String term;

    public PairCutoffTime(){
        this("", "", LocalDateTime.now().toString());
    }

    public PairCutoffTime(String base, String term, String dateTime) {
        super(base, dateTime);
        this.term = term;
    }

    public String getTerm() {
        return term;
    }
}
