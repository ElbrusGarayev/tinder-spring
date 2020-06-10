package app.controller;


import app.entity.Like;
import app.entity.User;
import app.service.LikeService;
import app.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Log4j2
@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final LikeService likeService;

    @GetMapping
    String handleLikePage(Model model, HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        User selected = userService.getUserToDisplay(user.getId());
        if(selected != null)
            model.addAttribute("person", selected);
        else
            return "redirect:/favorites";
        return "like-page";
    }

    @PostMapping
    String handleLikePage(HttpServletRequest req){
        String button = req.getParameter("button");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        User selected = userService.getUserToDisplay(user.getId());
        if (button.equals("dislike") && selected != null) {
            likeService.addLike(new Like(user.getId(), selected.getId(), false));
            return "redirect:/users";
        } else if (button.equals("like") && selected != null) {
            likeService.addLike(new Like(user.getId(), selected.getId(), true));
            return "redirect:/users";
        } else if (button.equals("likes")){
            return "redirect:/favorites";
        }
        else if (button.equals("logout")) {
            session.removeAttribute("user");
            return "redirect:/login";
        } else {
            return "redirect:/favorites";
        }
    }
}
