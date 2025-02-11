package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String index() {
        return "Home-page";
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us";
    }

    @PostMapping("/login")
    public String login() {
        return "Login-page";
    }

    @PostMapping("/Create-account")
    public String CreateAccountPage() {
        return "Create-account-page";
    }

}
