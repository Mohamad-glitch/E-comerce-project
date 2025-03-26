package com.example.ecommerce.DAO;

import com.example.ecommerce.DAO.interfaces.CartDAO;
import com.example.ecommerce.entity.Cart;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAOImpl implements CartDAO {

    private final EntityManager entityManager;

    @Autowired
    public CartDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveCart(Cart cart) {
        entityManager.persist(cart);
    }
}
