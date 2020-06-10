package app.dao;

import app.database.ConnectionDetails;
import app.database.DbConnection;
import app.entity.Like;
import app.heroku.HerokuEnv;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Service
public class DAOLikeSQL implements DAO<Like> {

//    private final Connection conn = DbConnection.create(ConnectionDetails.url,
//            ConnectionDetails.username, ConnectionDetails.password);
    private final Connection conn = DbConnection.createFromURL(HerokuEnv.jdbc_url());
    private final String SQLInsert = "insert into likes values(default, ?, ?, ?)";

    @Override
    public List<Like> getAll() {
        return null;
    }

    @Override
    public List<Like> getBy(int id) {
        return null;
    }

    @Override
    public Optional<Like> get(int id) {
        return Optional.empty();
    }

    @Override
    @SneakyThrows
    public void put(Like like) {
        PreparedStatement stmt = conn.prepareStatement(SQLInsert);
        stmt.setInt(1, like.getWho());
        stmt.setInt(2, like.getWhom());
        stmt.setBoolean(3, like.isStatus());
        stmt.execute();
    }
}
