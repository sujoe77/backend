package cutoff.jdbc;

public class JdbcConfig {
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/mydb?user=postgres&password=postgres";;

    private String instance;
    private String databaseName;
    private String integratedSecurity;
    private String authenticationScheme;
    private int maxPoolSize;
    private int minIdlePoolSize;
    private String user;
    private String password;

    public JdbcConfig(int maxPoolSize, int minIdlePoolSize) {
        this.maxPoolSize = maxPoolSize;
        this.minIdlePoolSize = minIdlePoolSize;
    }

    @Override
    public String toString() {
        return "JdbcConfig{" +
                "instance='" + instance + '\'' +
                ", databaseName='" + databaseName + '\'' +
                ", integratedSecurity='" + integratedSecurity + '\'' +
                ", authenticationScheme='" + authenticationScheme + '\'' +
                ", maxPoolSize=" + maxPoolSize +
                ", minIdlePoolSize=" + minIdlePoolSize +
                ", user='" + user + '\'' +
                '}';
    }

    public String getInstance() {
        return instance;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getIntegratedSecurity() {
        return integratedSecurity;
    }

    public String getAuthenticationScheme() {
        return authenticationScheme;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public int getMinIdlePoolSize() {
        return minIdlePoolSize;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
