package com.example.ecommerce.DAO;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager entityManager;
    private final CartDAO cartDAO;

    // constructor injection for dependency
    @Autowired
    public UserDAOImpl(EntityManager entityManager, CartDAO cartDAO) {
        this.entityManager = entityManager;
        this.cartDAO = cartDAO;
    }

    // saveProduct user
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
        Cart cart = new Cart();
        cart.addUser(user);
        cartDAO.saveCart(cart);

    }

    //get user by email
    @Override
    public User findUserByEmail(String email) {
        User user = entityManager.find(User.class, email);
        return user;
    }
}
