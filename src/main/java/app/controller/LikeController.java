package app.controller;

import app.dao.DAOLikeSQL;
import app.entity.User;
import app.service.LikeService;
import app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/favorites")
public class LikeController {

    private final UserService userService;

    @GetMapping
    String handleFavorites(Model model, HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("users", userService.getLikedUsers(user.getId()));
        return "people-list";
    }


}
