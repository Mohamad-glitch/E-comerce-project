package com.example.ecommerce.repoTest;


import com.example.ecommerce.entity.CartItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemDAO extends CrudRepository<CartItems, Integer> {

}
