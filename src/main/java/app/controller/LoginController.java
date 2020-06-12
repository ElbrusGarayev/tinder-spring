package app.controller;

import app.encryption.EncodeDecode;
import app.entity.User;
import app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final EncodeDecode enc;

    @GetMapping
    String handleLogin(){
        return "login";
    }

    @PostMapping
    String handle_login(Model model, User user, HttpServletRequest req){
        HttpSession session = req.getSession();
        if (userService.loginChecking(user.getEmail(), enc.encrypt(user.getPassword()))){{
            User loggedUser = userService
                    .getCurrentUser(user.getEmail(), enc.encrypt(user.getPassword())).get();
            session.setAttribute("user", loggedUser);
            userService.updateLastSeen(loggedUser.getId());
            return "redirect:/users";
        }}
        else
            model.addAttribute("message", "Email or Password is wrong!!!");
        return "login";
    }
}
