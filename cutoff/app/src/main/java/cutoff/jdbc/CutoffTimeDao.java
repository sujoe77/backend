package cutoff.jdbc;

import cutoff.model.CurrencyCutoffTime;
import cutoff.model.PairCutoffTime;

import java.time.LocalDate;
import java.util.List;

public class CutoffTimeDao {
    private static final String SQL_INSERT_CURRENCY_CUTOFF_UPDATES = "insert into cutoff_updates values (?,?,?,?) ";
    private static final String SQL_CHECK_TRADING_DATE_INITIALIZED = "select 1 from cutoff_updates where trading_date = ? ";
    private static final String SQL_INIT_TRADING_DATE = "insert into pair_cutoff select base, term, ? from pair_config pc ";
    private static final String SQL_UPDATE_CUTOFF = "update pair_cutoff set cutoff_time = ? where (base = ? or term = ?) and trading_date = ? and (cutoff_time > ? or cutoff_time is null) ";
    private static final String SQL_SELECT_UPDATED = "select * from pair_cutoff where (base = ? or term = ?) and trading_date = ? and cutoff_time = ? and cached = true";

    public void updateDB(CurrencyCutoffTime currencyCutoffTime) {
        addUpdates(currencyCutoffTime);
        LocalDate tradingDate = currencyCutoffTime.getDateTime().toLocalDate();
        if (!isTradingDateInitialized(tradingDate)) {
            initTradingDate(tradingDate);
        }
        updateCutoffTimes(currencyCutoffTime);
    }

    public int addUpdates(CurrencyCutoffTime currencyCutoffTime) {
        return 0;
    }

    public boolean isTradingDateInitialized(LocalDate date) {
        return false;
    }

    public int initTradingDate(LocalDate date) {
        return 0;
    }

    public int updateCutoffTimes(CurrencyCutoffTime currencyCutoffTime) {
        return 0;
    }

    public List<PairCutoffTime> getUpdated(CurrencyCutoffTime currencyCutoffTime) {
        return null;
    }
}
