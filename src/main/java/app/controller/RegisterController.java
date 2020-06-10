package app.controller;

import app.encryption.EncodeDecode;
import app.entity.User;
import app.form.FormData;
import app.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService regService;
    private final EncodeDecode enc;
    private final String DEFAULT_PHOTO = "https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png";

    @GetMapping
    String handleRegister(){
        return "signup";
    }

    @PostMapping
    String handleRegister(Model model, FormData form){
        if (!form.getPassword().equals(form.getPswRepeat())){
            model.addAttribute("message", "Password didn't match, try again! ");
        }
        else if (regService.checkValid(form.getEmail())){
            model.addAttribute("message", "User Already Exist!!!");
        }else {
            regService.register(new User(
                    form.getEmail(),
                    form.getName(),
                    form.getSurname(),
                    form.getStatus(),
                    enc.encrypt(form.getPswRepeat()),
                    form.getUrl().isEmpty() ? DEFAULT_PHOTO : form.getUrl()
            ));
            return "redirect:/login";
        }
        return "signup";
    }
}
