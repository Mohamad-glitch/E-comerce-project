package com.example.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {


    @Id
    @Column(name = "email")
    @NotNull(message = "is required")
    @Email
    private String email;

    @Column(name = "full_name")
    @NotNull(message = "is required")
    @Size(min = 5, max = 50 , message = "the name should have at least 5 characters and at most 50")
    private String fullName;

    @Column(name = "password")
    @NotNull(message = "is required")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "user",cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.MERGE})
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Orders> ordersList;

    public void addOrder(Orders orders) {
        if (ordersList == null) {
            ordersList = new ArrayList<>();
        }
        ordersList.add(orders);
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public User() {
    }

    public User(String email, String fullName, String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public Cart getCart() {
        return cart;
    }

    public void addCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
