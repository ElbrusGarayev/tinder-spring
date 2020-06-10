package app.dao;

import app.database.ConnectionDetails;
import app.database.DbConnection;
import app.entity.Message;
import app.heroku.HerokuEnv;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DAOMessageSQL implements DAO<Message> {

//    private final Connection conn = DbConnection.create(ConnectionDetails.url,
//            ConnectionDetails.username, ConnectionDetails.password);
    private final Connection conn = DbConnection.createFromURL(HerokuEnv.jdbc_url());
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm");
    private final String SQLGet = "select * from messages where " +
            "(sender = ? and receiver = ?) or " +
            "(sender = ? and receiver = ?) " +
            "order by id asc";
    private final String SQLInsert = "insert into messages values(default, ?, ?, localtimestamp, ?)";

    @Override
    public List<Message> getAll() {
        return null;
    }

    @Override
    public List<Message> getBy(int id) {
        return null;
    }

    @Override
    public Optional<Message> get(int id) {
        return Optional.empty();
    }

    @Override
    @SneakyThrows
    public void put(Message message) {
        PreparedStatement stmt = conn.prepareStatement(SQLInsert);
        stmt.setInt(1, message.getSender());
        stmt.setInt(2, message.getReceiver());
        stmt.setString(3, message.getContent());
        stmt.execute();
    }

    @SneakyThrows
    public List<Message> getAll(int sender, int receiver) {
        List<Message> messages = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement(SQLGet);
        stmt.setInt(1, sender);
        stmt.setInt(2, receiver);
        stmt.setInt(3, receiver);
        stmt.setInt(4, sender);
        ResultSet rSet = stmt.executeQuery();
        while (rSet.next()){
            messages.add(
                    new Message(
                            rSet.getInt("sender"),
                            rSet.getInt("receiver"),
                            rSet.getString("content"),
                            formatter.format(rSet.getTimestamp("datetime"))
                    )
            );
        }
        return messages;
    }
}
