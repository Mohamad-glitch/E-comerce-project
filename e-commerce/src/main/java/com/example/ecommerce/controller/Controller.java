package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@org.springframework.stereotype.Controller
public class Controller {


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


    @PostMapping("/login")
    public String login() {
        return "Login-page";
    }


    @PostMapping("/Create-account")
    public String CreateAccountPage() {
        return "Create-account-page";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "Login-page";
    }


    @GetMapping("/Create-account")
    public String CreateAccount() {
        return "Create-account-page";
    }




}
