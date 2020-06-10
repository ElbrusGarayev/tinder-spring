package app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String email;
    private String name;
    private String surname;
    private String status;
    private String password;
    private String url;
    private String lastSeen;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, String surname, String status, String url) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.url = url;
    }

    public User(int id, String name, String surname, String status, String url, String lastSeen) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.url = url;
        this.lastSeen = lastSeen;
    }

    public User(String email, String name, String surname, String status, String password, String url) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.password = password;
        this.url = url;
    }

    public User(int id, String name, String surname, String email, String password, String url, String lastSeen) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.url = url;
        this.lastSeen = lastSeen;
    }
}
