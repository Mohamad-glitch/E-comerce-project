package com.example.ecommerce.repoTest;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItems;
import com.example.ecommerce.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemDAOImpl implements CartItemDAO {

    private final EntityManager entityManager;

    @Autowired
    public CartItemDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveItemQuantity(Long quantity) {

    }

    @Override
    @Transactional
    public void saveCartItems(CartItems cartItems) {
        entityManager.persist(cartItems);
    }
}
