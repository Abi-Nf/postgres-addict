package postgres.addict;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Pooled implements ConnectionPool {
  private static int INITIAL_POOL_SIZE = 10;
  private String url;
  private String user;
  private String password;
  private List<Connection> connectionPool;
  private List<Connection> usedConnections = new ArrayList<>();

  public Pooled(String url, String user, String password, List<Connection> pool) {
    this.url = url;
    this.user = user;
    this.password = password;
    this.connectionPool = pool;
  }

  @SneakyThrows
  public static Pooled create(String url, String user, String password) {
    List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);

    for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
      pool.add(createConnection(url, user, password));
    }
    return new Pooled(url, user, password, pool);
  }

  @SneakyThrows
  private static Connection createConnection(String url, String user, String password) {
    return DriverManager.getConnection(url, user, password);
  }

  @Override
  public Connection getConnection() {
    Connection connection = connectionPool
        .remove(connectionPool.size() - 1);
    usedConnections.add(connection);
    return connection;
  }

  @Override
  public boolean releaseConnection(Connection connection) {
    connectionPool.add(connection);
    return usedConnections.remove(connection);
  }
}
