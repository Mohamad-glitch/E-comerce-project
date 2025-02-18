package com.example.ecommerce.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_email")
    private String username;

    @Column(name = "total_price")
    private double price;

    @Column(name = "order_date")
    private Timestamp date;

    public Orders() {
    }

    public Orders(String username, double price, Timestamp date) {
        this.username = username;
        this.price = price;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
                ", username='" + username + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
