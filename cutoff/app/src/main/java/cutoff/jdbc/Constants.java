package cutoff.jdbc;

public class Constants {
    public static final String SQL_SELECT_CUTOFF_TIME = "select cutoff_time from pair_cutoff where trading_date = ? and ((base = ? and term = ?) or (base = ? and term = ?)) ";
    public static final String SQL_SELECT_ALL_CACHED_CUTOFF_TIME_FOR_DATE = "select cut.* \n" +
            "from pair_cutoff cut join pair_config con\n" +
            "on cut.base = con.base\n" +
            "and cut.term = con.term\n" +
            "and cached = true\n" +
            "and trading_date = ?\n";
    public static final String SQL_SELECT_UPDATED = "select cut.* \n" +
            "from pair_cutoff cut join pair_config con\n" +
            "on cut.base = con.base\n" +
            "and cut.term = con.term\n" +
            "and cached = true\n" +
            "and (cut.base = ? or cut.term = ?)\n" +
            "and trading_date = ?\n" +
            "and cutoff_time = ?";
    public static final String SQL_INSERT_CURRENCY_CUTOFF_UPDATES = "insert into cutoff_updates values (?,?,?,?) ";
    public static final String SQL_CHECK_TRADING_DATE_INITIALIZED = "select 1 from pair_cutoff where trading_date = ? ";
    public static final String SQL_INIT_TRADING_DATE = "insert into pair_cutoff select base, term, ? from pair_config pc ";
    public static final String SQL_UPDATE_CUTOFF = "update pair_cutoff set cutoff_time = ? where (base = ? or term = ?) and trading_date = ? and (cutoff_time > ? or cutoff_time is null) ";
    public static final String SQL_INSERT_PAIR_CONFIG = "insert into pair_config values (?,?,?) ";
}
