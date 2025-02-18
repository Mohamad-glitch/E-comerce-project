package com.example.ecommerce.service;

import com.example.ecommerce.DAO.CartDAO;
import com.example.ecommerce.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    CartDAO cartDAO;

    @Autowired
    public CartService(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    void SaveCart(Cart cart) {
        cartDAO.saveCart(cart);
    }


}
