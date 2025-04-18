package com.example.ecommerce.service;

import com.example.ecommerce.DAO.interfaces.CartDAO;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.service.interfaces.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    CartDAO cartDAO;

    @Autowired
    public CartServiceImpl(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Transactional
    public void saveCart(Cart cart) {
        cartDAO.saveCart(cart);
    }


}
