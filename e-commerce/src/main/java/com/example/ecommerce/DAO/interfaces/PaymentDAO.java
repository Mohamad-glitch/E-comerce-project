package com.example.ecommerce.DAO.interfaces;

import com.example.ecommerce.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaymentDAO extends JpaRepository<Payment, Long> {

    Payment getPaymentById(long id);

    List<Payment> getPaymentByUserEmail(String email);



}
