package com.example.ecommerce.controller;


import com.example.ecommerce.DAO.ProductDAOImpl;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.repoTest.CartItemDAO;
import com.example.ecommerce.repoTest.CartItemsService;
import com.example.ecommerce.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@org.springframework.stereotype.Controller
public class Controller {
    private final ProductDAOImpl productDAOImpl;
    UserService userService;
    ProductService productService;
    CartService cartService;
    CartItemDAO cartItemDAO;
    OrderService orderService;
    CartItemsService cartItemsService;


    @Autowired
    public Controller(UserServiceImpl userService, ProductServiceImpl
            productService, CartServiceImpl cartService, CartItemDAO cartItemDAO, CartItemsService cartItemsService,
                      ProductDAOImpl productDAOImpl, OrderServiceImpl orderService) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
        this.productDAOImpl = productDAOImpl;
        this.cartItemDAO = cartItemDAO;
        this.orderService = orderService;
        this.cartItemsService = cartItemsService;
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


    // this is not functioning well it will have no use
    @GetMapping("/buy-now")
    public String buyNow(Model model , @RequestParam Integer productId,
                          @RequestParam("userAmount") int userAmount, Principal principal) {
        Product product = productService.getProductById(productId);
        User user = userService.findUserByEmail(principal.getName());

        System.out.println(userAmount);


        Orders order = new Orders((userAmount * product.getPrice()), new Timestamp(System.currentTimeMillis()));
        order.addProduct(product);
        order.addUser(user);

        Payment payment = new Payment();
        payment.addUser(user);

        model.addAttribute("price", (userAmount * product.getPrice()));
        model.addAttribute("product", product);
        model.addAttribute("userAmount", userAmount);
        model.addAttribute("payment", payment);

        return "buy-now";
    }


    // this method will save the product the user picked and save it in cart-items then send user to shop page
    @GetMapping("/add-to-cart")
    public String addToCart(Model model , @RequestParam int productId, RedirectAttributes redirectAttributes,
                            @RequestParam("userAmount") int userAmount, Principal principal) {

        // get the item and amount of product and associate it to the user in user cart
        Product userProduct = productService.getProductById(productId);
        User user = userService.findUserByEmail(principal.getName());
        Cart userCart = user.getCart();

        //check if there was already this item in the cart if yes add to it else save new product in DB
        CartItems temp = cartItemDAO.getCartItemsByCartAndProduct(userCart, userProduct);
        if(temp == null) {

            // create new cart item to save the item in the cart
            CartItems cartItems = new CartItems(userCart, userProduct, userAmount);

            cartItemDAO.save(cartItems);
        }else{

            // check if the amount of product available in stock if yes update user amount if no return note
            if((temp.getQuantity() + userAmount) > userProduct.getQuantity()){
                long available = userProduct.getQuantity() - temp.getQuantity();

                redirectAttributes.addFlashAttribute("errorMessage",
                        "Only " + available + " items left in stock! Couldn't add more to your cart.");
            }else{
                long stillAvailable = userProduct.getQuantity()-(userAmount + temp.getQuantity());
                temp.setQuantity(temp.getQuantity() + userAmount);
                userProduct.setQuantity((int)stillAvailable);
                productService.saveProduct(userProduct);
                cartItemDAO.save(temp);

            }

        }




        return "redirect:/shop";
    }


    //TODO: 2- give the user the advantage to change the product amount and remove it
    //TODO: 3- when user press checkout button take him to paying method page
    @GetMapping("/cart-page")
    public String cartPage(Principal principal, Model model) {

        // get user items count the total price of the items
        User user = userService.findUserByEmail(principal.getName());
        List<CartItems> userItems = cartItemDAO.getCartItemsByCart(user.getCart());
        List<Product> products = new ArrayList<>();
        List<Long> userAmounts = new ArrayList<>();
        double total = 5;

        for(CartItems cartItem : userItems) {
            Product product = productService.getProductById(cartItem.getProduct().getId());
            long userAmount = cartItem.getQuantity();


            total += userAmount * product.getPrice();

            product.setUserAmount(userAmount);
            products.add(product);

        }

        // return the items in model to cart page
        model.addAttribute("products", products);
        model.addAttribute("userAmount", userItems);
        model.addAttribute("totalPrice", total);


        return "cart-page";
    }

    @PostMapping("/remove-from-cart/{cartItem}")
    public String removeFromCart(Model model, @PathVariable int cartItem) {
        cartItemsService.deleteCartItemById(cartItem);


        return "redirect:/cart-page";
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
