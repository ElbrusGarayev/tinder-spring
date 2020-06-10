package app.controller;

import app.entity.Message;
import app.entity.User;
import app.service.Formatter;
import app.service.MessageService;
import app.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/chat")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final Formatter fmt;

    @GetMapping("/*")
    String handleChat(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        else {
            int receiver = Integer.parseInt(fmt.getIdFromPath(req.getServletPath()));
            model.addAttribute("sender", user.getId());
            model.addAttribute("messages", messageService.getMessages(user.getId(), receiver));
            model.addAttribute("user", userService.getUser(receiver));
        }
        return "chat";
    }

    @PostMapping("/*")
    String handleChat(HttpServletRequest req, @RequestParam String content) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int receiver = Integer.parseInt(fmt.getIdFromPath(req.getServletPath()));
        if (content.trim().length() > 0)
            messageService.addMessage(new Message(user.getId(), receiver, content));
        return "redirect:/chat/" + receiver;
    }
}
