package com.example.ecommerce.service.interfaces;

import com.example.ecommerce.entity.Payment;

import java.util.List;

public interface PaymentService {

     void SavePayment(Payment payment);

     List<Payment> getAllPayments();

     List<Payment> getPaymentByUserEmail(String email);

    void save(Payment userInfo);
}
