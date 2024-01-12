package postgres.addict;

public class DbConfig {
  public void setDatabaseUrl(String url){
    AppEnv.DB_URL = url;
    AppEnv.checkCreationValidity();
  }

  public void setDatabaseUser(String user){
    AppEnv.DB_USER = user;
    AppEnv.checkCreationValidity();
  }

  public void setDatabasePassword(String password){
    AppEnv.DB_PASSWORD = password;
    AppEnv.checkCreationValidity();
  }
}
