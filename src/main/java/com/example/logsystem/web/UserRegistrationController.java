package com.example.logsystem.web;


import com.example.logsystem.service.UserService;
import com.example.logsystem.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//remove the default log  in page in springboot
//difference between request mapping and get mapping
@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

     @ModelAttribute("user")
     public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();

    }




     @GetMapping()
    public String showRegistrationForm(){

        return "registration";
    }

     @PostMapping
    public String registerUserAccount(@ModelAttribute("user")UserRegistrationDto registrationDto){
        userService.save(registrationDto);
        return  "redirect:/registration?success";

    }
}
