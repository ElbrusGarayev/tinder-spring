package app.dao;

import app.database.ConnectionDetails;
import app.database.DbConnection;
import app.entity.User;
import app.heroku.HerokuEnv;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class DAOUserSQL implements DAO<User> {

//    private final Connection conn = DbConnection.create(ConnectionDetails.url,
//            ConnectionDetails.username, ConnectionDetails.password);
    private final Connection conn = DbConnection.createFromURL(HerokuEnv.jdbc_url());
    private final String SQLGetAll = "select * from users";
    private final String SQLGetBy = "select u.* from users u where " +
            "u.id not in(select whom from likes where who = ?) and u.id != ?";
    private final String SQLGet = "select id from users where " +
            "email = ? and password = ?";
    private final String SQLGetLikes = "select * from users where id " +
            "in(select whom from likes where who = ? and status = true)";
    private final String SQLGetById = " select * from users where id = ?";
    private final String SQLInsert = "insert into users values(default, ?, ?, ?, ?, ?, ?, localtimestamp)";
    private final String SQLUpdate = "update users set lastSeen = localtimestamp where id = ?";

    @Override
    @SneakyThrows
    public List<User> getAll() {
        PreparedStatement stmt = conn.prepareStatement(SQLGetAll);
        ResultSet rSet = stmt.executeQuery();
        List<User> users = new ArrayList<>();
        while (rSet.next()){
            User user = new User(
                    rSet.getInt("id"),
                    rSet.getString("email"),
                    rSet.getString("password"));
            users.add(user);
        }
        return users;
    }

    @Override
    @SneakyThrows
    public List<User> getBy(int id) {
        List<User> users = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement(SQLGetBy);
        stmt.setInt(1, id);
        stmt.setInt(2, id);
        ResultSet rSet = stmt.executeQuery();
        while (rSet.next()){
            int userId = rSet.getInt("id");
            String name = rSet.getString("name");
            String surname = rSet.getString("surname");
            String status = rSet.getString("status");
            String url = rSet.getString("url");
            users.add(new User(userId, name, surname, status, url));
        }
        return users;
    }

    @Override
    @SneakyThrows
    public Optional<User> get(int id) {
        PreparedStatement stmt = conn.prepareStatement(SQLGetById);
        stmt.setInt(1, id);
        ResultSet rSet = stmt.executeQuery();
        while (rSet.next()){
            return Optional.of(new User(
                    rSet.getInt("id"),
                    rSet.getString("name"),
                    rSet.getString("surname"),
                    rSet.getString("email"),
                    rSet.getString("password"),
                    rSet.getString("url"),
                    rSet.getString("lastSeen")
            ));
        }
        return Optional.empty();
    }

    @SneakyThrows
    public Optional<User> get(User user){
        int id = 0;
        PreparedStatement stmt = conn.prepareStatement(SQLGet);
        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getPassword());
        ResultSet rSet = stmt.executeQuery();
        while (rSet.next()) id = rSet.getInt("id");
        return get(id);
    }

    @Override
    @SneakyThrows
    public void put(User user) {
        PreparedStatement stmt = conn.prepareStatement(SQLInsert);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getSurname());
        stmt.setString(3, user.getStatus());
        stmt.setString(4, user.getEmail());
        stmt.setString(5, user.getPassword());
        stmt.setString(6, user.getUrl());
        stmt.execute();
    }

    @SneakyThrows
    public List<User> getAllByPredicate(int id){
        List<User> users = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement(SQLGetLikes);
        stmt.setInt(1, id);
        ResultSet rSet = stmt.executeQuery();
        while (rSet.next()){
            users.add(
                    new User(
                    rSet.getInt("id"),
                    rSet.getString("name"),
                    rSet.getString("surname"),
                    rSet.getString("status"),
                    rSet.getString("url"),
                    rSet.getString("lastSeen"))
            );
        }
        return users;
    }

    @SneakyThrows
    public void update(int id){
        PreparedStatement stmt = conn.prepareStatement(SQLUpdate);
        stmt.setInt(1, id);
        stmt.execute();
    }
}
