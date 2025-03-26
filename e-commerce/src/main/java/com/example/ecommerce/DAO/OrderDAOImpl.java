package com.example.ecommerce.DAO;

import com.example.ecommerce.DAO.interfaces.OrderDAO;
import com.example.ecommerce.entity.Orders;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private final EntityManager entityManager;

    @Autowired
    public OrderDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveOrder(Orders order) {
        entityManager.persist(order);
    }
}
