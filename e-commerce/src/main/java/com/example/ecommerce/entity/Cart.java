package com.example.ecommerce.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;

    public User getUser() {
        return user;
    }

    public void addUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItems> cartItems;

    public List<CartItems> getCartItems() {
        return cartItems;
    }

    public void addCartItem(CartItems cartItem) {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        cartItems.add(cartItem);
        cartItem.setCart(this); // Maintain bidirectional relationship
    }



    public Cart() {
    }

    public Cart( User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
