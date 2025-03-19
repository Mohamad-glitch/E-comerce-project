package com.example.ecommerce.service;

import com.example.ecommerce.entity.Payment;

import java.util.List;

public interface PaymentService {

    public void SavePayment(Payment payment);

    public Payment getPaymentById(long id);

    public List<Payment> getAllPayments();

    public void updatePayment(Payment payment);

    public void deletePaymentById(long id);
}
