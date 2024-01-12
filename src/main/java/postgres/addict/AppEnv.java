package postgres.addict;

import lombok.SneakyThrows;

public class AppEnv {
  protected static String DB_URL = "";
  protected static String DB_USER = "";
  protected static String DB_PASSWORD = "";
  public static Pooled POOL_CONNECTION;

  @SneakyThrows
  public static void iniPool(){
    POOL_CONNECTION = Pooled.create(DB_URL, DB_USER, DB_PASSWORD);
  }

  public static void checkCreationValidity(){
    if(!(
        DB_URL.isEmpty() &&
        DB_USER.isEmpty() &&
        DB_PASSWORD.isEmpty())
    ){
      iniPool();
    }
  }
}
