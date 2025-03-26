package com.example.ecommerce.service;

import com.example.ecommerce.DAO.interfaces.OrderDAO;
import com.example.ecommerce.entity.Orders;
import com.example.ecommerce.service.interfaces.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl  implements OrderService {

    private final OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }


    @Override
    @Transactional
    public void saveOrder(Orders order) {
        orderDAO.saveOrder(order);
    }
}
