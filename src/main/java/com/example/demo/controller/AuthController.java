package com.example.demo.controller;
import com.example.demo.model.UserEntity;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@AllArgsConstructor
@Controller
public class AuthController {

    private UserService userService;

    @GetMapping("/login")
    public String loginPage(Model model){
        return "login";
    }

    @GetMapping("/register")
    public String getRegistration(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") UserEntity user,
                           BindingResult result, Model model) {
        UserEntity existingEmail = userService.findByEmail(user.getEmail());
        if (existingEmail != null) {
            result.rejectValue("email", "error.email", "There is already a user who uses this email");
        }

        UserEntity existingUsername = userService.findByUsername(user.getUsername());
        if (existingUsername != null) {
            result.rejectValue("username", "error.username", "There is already a user who uses this username");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);

        return "redirect:/currency?success";
    }
}
