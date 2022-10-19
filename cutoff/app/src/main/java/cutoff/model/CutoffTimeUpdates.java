package cutoff.model;

import java.util.ArrayList;
import java.util.List;

public class CutoffTimeUpdates {
    private List<CurrencyCutoffTime> updates;

    public CutoffTimeUpdates() {
        this(new ArrayList<>());
    }

    public CutoffTimeUpdates(List<CurrencyCutoffTime> updates) {
        this.updates = updates;
    }

    public List<CurrencyCutoffTime> getUpdates() {
        return updates;
    }
}
