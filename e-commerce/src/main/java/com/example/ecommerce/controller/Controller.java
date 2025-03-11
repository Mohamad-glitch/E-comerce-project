package com.example.ecommerce.controller;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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
    // note if the user saved in the database the role should be like this ROLE_(the role u want )
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

    // show the page to add product to admin only
    @GetMapping("/add-product")
    public String addProduct(Model model) {
       model.addAttribute("product", new Product());
        return "add-product";
    }

    // save the item in database
    @PostMapping("/saveItem")
    public String saveItem(@ModelAttribute Product product, @RequestParam("item-photo") MultipartFile photo) {
        String path = fileHandling(photo);
        product.setImagePath( path);
        productService.saveProduct(product);

        return "redirect:/user-page";
    }



    // file handling
    public String fileHandling(MultipartFile itemPhoto
    ) {

        try {
            // Define the path to the directory where images will be saved
            String directoryPath = "C:\\Users\\mshlo\\OneDrive\\Desktop\\images";
            File directory = new File(directoryPath);

            // Create the directory if it doesn't exist
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generate a unique file name to avoid conflicts
            String fileName = System.currentTimeMillis() + "_" + itemPhoto.getOriginalFilename();
            Path filePath = Paths.get(directoryPath, fileName);

            // Save the file to the directory
            Files.write(filePath, itemPhoto.getBytes());

            // Return the absolute path of the saved file
            return filePath.toAbsolutePath().toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save image: " + e.getMessage());
        }

    }


}
