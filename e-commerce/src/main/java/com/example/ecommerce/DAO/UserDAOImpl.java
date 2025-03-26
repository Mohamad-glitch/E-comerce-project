package com.example.ecommerce.DAO;

import com.example.ecommerce.DAO.interfaces.CartDAO;
import com.example.ecommerce.DAO.interfaces.UserDAO;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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


    }

    //get user by email
    @Override
    public User findUserByEmail(String email) {
        User user = entityManager.find(User.class, email);
        return user;
    }

    // get all users emails
    @Override
    public List<User> findAllUsers() {
        return entityManager.createQuery("select email from User ", User.class).getResultList();
    }

    // update user by email
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    // remove user
    @Override
    public void deleteUserByEmail(String email) {
        entityManager.remove(findUserByEmail(email));
    }

    @Override
    public void updateUserRole(String email, String role) {
        User temp = findUserByEmail(email);
        temp.setRole(role);
        entityManager.merge(temp);
    }

    public Cart findUserCartByEmail(String email){
        User temp = findUserByEmail(email);
        if(temp.getCart() != null){
            temp.addCart(new Cart());
        }

        return temp.getCart();
    }


}
