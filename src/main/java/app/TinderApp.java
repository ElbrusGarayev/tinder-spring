package app;

import app.database.DbSetup;
import app.heroku.HerokuEnv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinderApp {
    public static void main(String[] args) {
        DbSetup.migrate(HerokuEnv.jdbc_url(), HerokuEnv.jdbc_username(), HerokuEnv.jdbc_password());
        SpringApplication.run(TinderApp.class);
    }
}
