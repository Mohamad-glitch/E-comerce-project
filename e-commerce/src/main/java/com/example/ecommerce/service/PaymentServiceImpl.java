package com.example.ecommerce.service;

import com.example.ecommerce.DAO.PaymentDAO;
import com.example.ecommerce.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDAO paymentDAO;

    @Autowired
    public PaymentServiceImpl(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }


    public void SavePayment(Payment payment) {
        paymentDAO.save(payment);
    }

    public Payment getPaymentById(long id) {
        return paymentDAO.getPaymentsById(id);
    }

    public List<Payment> getAllPayments() {
        return paymentDAO.findAll();
    }

    public void updatePayment(Payment payment) {
       paymentDAO.save(payment);
    }

    public void deletePaymentById(long id) {
        paymentDAO.deletePaymentById(id);
    }


}
