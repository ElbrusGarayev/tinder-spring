package app.service;

import org.springframework.stereotype.Service;

@Service
public class Formatter {
    public String getIdFromPath(String path){
        return path.chars()
                .filter(at -> Character.isDigit(at))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                        StringBuilder::append).toString();
    }
}
