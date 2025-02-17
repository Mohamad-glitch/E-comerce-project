package com.example.ecommerce.controller;


import org.springframework.web.bind.annotation.GetMapping;


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



    @GetMapping("/login")
    public String loginPage() {
        return "Login-page";
    }


    @GetMapping("/Create-account")
    public String CreateAccount() {
        return "Create-account-page";
    }




}
