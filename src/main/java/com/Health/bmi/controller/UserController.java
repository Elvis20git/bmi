package com.Health.bmi.controller;

//import com.Employee3.model.Employee;
//import com.Employee3.service.EmployeeService;
import com.Health.bmi.model.User;
import com.Health.bmi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/")
    public String homePage(Model model){
//        model.addAttribute("registerRequest", new User());
        return "index";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new User());
        return "registration_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new User());
        return "login_page";
    }



        @PostMapping("/register")
    public String register(@ModelAttribute User user){
            System.out.println("register request :" + user);
            User registeredUser = userService.registeruser(user.getLogin(), user.getEmail(), user.getPassword());
            return registeredUser == null ? "error_page" : "redirect:/login";

        }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model){
        System.out.println("login request :" + user);
        User authenticated = userService.authenticate(user.getEmail(), user.getPassword());
        if(authenticated !=null){
            model.addAttribute("userLogin", authenticated.getLogin());
            return "personalAccountPage";
        }else {
            return "error_page";
        }

    }


}
