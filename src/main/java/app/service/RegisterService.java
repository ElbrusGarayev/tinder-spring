package app.service;

import app.dao.DAOUserSQL;
import app.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterService {

    private final DAOUserSQL userDao;

    public boolean checkValid(String email){
        return userDao.getAll().stream().anyMatch(user -> user.getEmail().equals(email));
    }

    public void register(User user){
        userDao.put(user);
    }
}
