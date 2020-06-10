package app.database;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

@Service
public class DbConnection {
    @SneakyThrows
    public static Connection create(String url, String username, String password){
        return DriverManager.getConnection(url, username, password);
    }

    @SneakyThrows
    public static Connection createFromURL(String url){
        return DriverManager.getConnection(url);
    }

}
