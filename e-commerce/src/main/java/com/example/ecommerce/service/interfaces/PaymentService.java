package com.example.ecommerce.service.interfaces;

import com.example.ecommerce.entity.Payment;

import java.util.List;

public interface PaymentService {

    public void SavePayment(Payment payment);



    public List<Payment> getAllPayments();

    public void updatePayment(Payment payment);


}
