package cutoff.model;

import java.util.List;

public class CutoffTimeUpdates {
    private final List<CurrencyCutoffTime> updates;

    public CutoffTimeUpdates(List<CurrencyCutoffTime> updates) {
        this.updates = updates;
    }

    public List<CurrencyCutoffTime> getUpdates() {
        return updates;
    }
}
