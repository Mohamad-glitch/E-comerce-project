package com.example.ecommerce.controller;


import com.example.ecommerce.entity.User;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@org.springframework.stereotype.Controller
public class Controller {


    UserService userService;

    @Autowired
    public Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "Home-page";
    }

    @GetMapping("/user-page")
    public String home() {
        return "Home-Page-Account";
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us";
    }



    @GetMapping("/login")
    public String loginPage() {
        return "Login-page";
    }


    @PostMapping("/Create-account")
    public String createAccount(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/Create-account")
    public String CreateAccount(Model model) {
          model.addAttribute("user",new User());

        return "Create-account-page";
    }




}
