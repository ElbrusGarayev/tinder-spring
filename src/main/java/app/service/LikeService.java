package app.service;

import app.dao.DAOLikeSQL;
import app.entity.Like;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeService {

    private final DAOLikeSQL daoLike;

    public void addLike(Like like){
        daoLike.put(like);
    }
}
