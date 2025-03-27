package com.example.ecommerce.service;

import com.example.ecommerce.DAO.interfaces.PaymentDAO;
import com.example.ecommerce.entity.Payment;
import com.example.ecommerce.service.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDAO paymentDAO;

    @Autowired
    public PaymentServiceImpl(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Transactional
    public void SavePayment(Payment payment) {
        paymentDAO.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentDAO.findAll();
    }

    @Override
    public List<Payment> getPaymentByUserEmail(String email) {
        return paymentDAO.getPaymentByUserEmail(email);
    }

    @Override
    public void save(Payment userInfo) {
        paymentDAO.save(userInfo);
    }


}
