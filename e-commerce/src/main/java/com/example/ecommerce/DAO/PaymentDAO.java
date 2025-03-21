package com.example.ecommerce.DAO;

import com.example.ecommerce.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentDAO extends JpaRepository<Payment, Long> {

}
