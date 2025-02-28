package com.example.ecommerce.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "total_price")
    private double price;

    @Column(name = "order_date")
    private Timestamp date;

    /*
    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;
     */


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productList;

    public void addProduct(Product product) {
        if(productList == null) {
            productList = new ArrayList<>();
        }

        productList.add(product);
    }

    public List<Product> getProductList() {
        return productList;
    }


    public Orders() {
    }

    public Orders( double price, Timestamp date) {
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
/*
public void setUser(User user) {
        this.user = user;
    }
public void addUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

*/
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
