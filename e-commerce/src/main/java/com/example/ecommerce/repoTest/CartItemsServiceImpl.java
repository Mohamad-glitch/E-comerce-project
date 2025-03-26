package com.example.ecommerce.repoTest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartItemsServiceImpl implements CartItemsService {

    private final CartItemDAO cartItemDAO;

    @Autowired
    public CartItemsServiceImpl(CartItemDAO cartItemDAO) {
        this.cartItemDAO = cartItemDAO;
    }

    @Override
    @Transactional
    public void deleteCartItemById(int id) {
        System.out.println("deleting cart item by id: " + id);
       cartItemDAO.deleteCartItemsById(id);

    }
}
