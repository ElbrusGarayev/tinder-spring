package app.service;

import app.dao.DAOMessageSQL;
import app.entity.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final DAOMessageSQL daoMessage;

    public List<Message> getMessages(int sender, int receiver){
        return daoMessage.getAll(sender, receiver);
    }

    public void addMessage(Message message){
        daoMessage.put(message);
    }
}
