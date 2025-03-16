package com.example.ecommerce.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "stock_quantity")
    private int quantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItems> cartItems = new ArrayList<>();


    public List<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItems> cartItems) {
        this.cartItems = cartItems;
    }


    // Helper method to add CartItems
    public void addCartItems(CartItems cartItem) {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        cartItems.add(cartItem);
        cartItem.setProduct(this); // Maintain bidirectional relationship
    }


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Orders> orderList;

    public void addOrder(Orders order) {
        if(orderList == null) {
            orderList = new ArrayList<>();
        }

        orderList.add(order);
    }



    public Product() {
    }

    public Product(String name, String category, String imagePath, String description, double price, int quantity) {
        this.name = name;
        this.category = category;
        this.imagePath = imagePath;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
