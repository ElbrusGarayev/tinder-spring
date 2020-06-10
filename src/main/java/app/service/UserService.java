package app.service;



import app.dao.DAOUserSQL;
import app.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final DAOUserSQL daoUser;

    public User getUserToDisplay(int id){
        List<User> likedUsers = daoUser.getBy(id);
        if (likedUsers.size() > 0) {
            return likedUsers.get(0);
        }
        return null;
    }

    public boolean loginChecking(String email, String password){
        return daoUser.getAll().stream()
                .anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }

    public Optional<User> getCurrentUser(String email, String password) {
        return daoUser.get(new User(email, password));
    }

    public List<User> getLikedUsers(int id){
        return daoUser.getAllByPredicate(id);
    }

    public User getUser(int id){
        return daoUser.get(id).get();
    }

    public void updateLastSeen(int id){
        daoUser.update(id);
    }
}
