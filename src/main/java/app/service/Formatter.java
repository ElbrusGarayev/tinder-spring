package app.service;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class Formatter {
    public String getIdFromPath(String path){
        return path.chars()
                .filter(at -> Character.isDigit(at))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                        StringBuilder::append).toString();
    }
}
