package com.example.ecommerce.controller;


import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@org.springframework.stereotype.Controller
public class Controller {
    UserService userService;
    ProductService productService;
    CartService cartService;


    @Autowired
    public Controller(UserServiceImpl userService, ProductServiceImpl
            productService, CartServiceImpl cartService
                     ) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;

    }

    // show home page for every one if he was logged in or not
    @GetMapping("/")
    public String index() {

        return "Home-page";
    }

    // show the user page for user after login
    @GetMapping("/user-page")
    public String home() {
        return "Home-Page-Account";
    }

    // show the about us page
    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us";
    }


    // show the login page
    @GetMapping("/login")
    public String loginPage() {
        return "Login-page";
    }

    // show create account page and has user validation that made on user entity and check if the email was in the database or not
    // create new user using http methods
    @PostMapping("/Create-account")
    public String createAccount(@Valid @ModelAttribute User user, BindingResult result) {

        // Validate the raw password
        String password = user.getPassword();
        if (!password.matches("^[a-zA-Z0-9!@#$%^&*()_+=-]{5,50}$")) {
            result.rejectValue("password", "password.invalid", "Password must be 5-50 characters, including letters, digits, and special characters.");
        }

        if(result.hasErrors()) {
            return "Create-account-page";
        }

        User temp = userService.findUserByEmail(user.getEmail());
        if(temp != null) {
            result.rejectValue("email", "email.exists", "There is already an account with that email");
            //free the memory
            temp = null;
            return "Create-account-page";
        }

        userService.saveUser(user);

        return "redirect:/login";
    }

    // show create account page
    @GetMapping("/Create-account")
    public String CreateAccount(Model model) {
          model.addAttribute("user",new User());

        return "Create-account-page";
    }

    // show shop page "still working on it"
    @GetMapping("/shop")
    public String shop() {
        return "shop-page";
    }

}
