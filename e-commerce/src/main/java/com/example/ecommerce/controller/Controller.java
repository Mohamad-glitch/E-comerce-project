package com.example.ecommerce.controller;


import com.example.ecommerce.DAO.ProductDAOImpl;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.repoTest.CartItemDAO;
import com.example.ecommerce.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Controller
public class Controller {
    private final ProductDAOImpl productDAOImpl;
    UserService userService;
    ProductService productService;
    CartService cartService;
    CartItemDAO cartItemDAO;
    OrderService orderService;


    @Autowired
    public Controller(UserServiceImpl userService, ProductServiceImpl
            productService, CartServiceImpl cartService, CartItemDAO cartItemDAO,
                      ProductDAOImpl productDAOImpl, OrderServiceImpl orderService) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
        this.productDAOImpl = productDAOImpl;
        this.cartItemDAO = cartItemDAO;
        this.orderService = orderService;
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
        // check if the user email is already in database if (yes) return message error
        User temp = userService.findUserByEmail(user.getEmail());
        if(temp != null) {
            result.rejectValue("email", "email.exists", "There is already an account with that email");
            //free the memory
            temp = null;
            return "Create-account-page";
        }
        user.setRole("ROLE_USER");

        // create a cart for every user when is created
        Cart cart = new Cart();
        cart.addUser(user);
        user.addCart(cart);

        userService.saveUser(user);

        return "redirect:/login";
    }

    // show create account page
    @GetMapping("/Create-account")
    public String CreateAccount(Model model) {
          model.addAttribute("user",new User());

        return "Create-account-page";
    }

    // shop page work fine
    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("products", productService.getAllProducts());
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

    // this for access denied page if the user is not authorized to go the page, then it will appear this page instead
    //this page will send hit to home page after login (he does not have to log in again)
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "not-authorize";
    }

    @GetMapping("/buy-now")
    public String buyNow( Model model , @RequestParam int productId,
                          @RequestParam Long quantity, Principal principal) {
        Product product = productService.getProductById(productId);
        User user = userService.findUserByEmail(principal.getName());

        Orders order = new Orders((quantity * product.getPrice()), new Timestamp(System.currentTimeMillis()));
        order.addProduct(product);
        order.addUser(user);


        Payment payment = new Payment();
        payment.addUser(user);

        model.addAttribute("price", (quantity * product.getPrice()));
        model.addAttribute("product", product);
        model.addAttribute("quantity", quantity);
        model.addAttribute("payment", payment);

        return "buy-now";
    }


    @GetMapping("/add-to-cart")
    public String addToCart(Model model , @RequestParam int productId,
                            @RequestParam Long quantity, Principal principal) {
        User temp = userService.findUserByEmail(principal.getName());

        return "add-to-cart";
    }


    // file handling
    public String fileHandling(MultipartFile itemPhoto
    ) {

        try {
            // Define the path to the directory where images will be saved
            String directoryPath = "src/main/resources/static/product-images";
            String photoNameInDB = "/product-images/";
            File directory = new File(directoryPath);

            // Create the directory if it doesn't exist
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generate a unique file name to avoid conflicts
            String fileName = System.currentTimeMillis() + "_" + itemPhoto.getOriginalFilename();
            photoNameInDB += fileName;
            Path filePath = Paths.get(directoryPath, fileName);

            // Save the file to the directory
            Files.write(filePath, itemPhoto.getBytes());

            // Return the absolute path of the saved file
            return photoNameInDB;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save image: " + e.getMessage());
        }

    }


}
