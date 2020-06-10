package app.service;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class Formatter {
    public String getIdFromPath(String path){
        return path.chars()
                .filter(at -> Character.isDigit(at))
                .mapToObj(Character::toString)
                .collect(Collectors.joining(""));
    }
}
