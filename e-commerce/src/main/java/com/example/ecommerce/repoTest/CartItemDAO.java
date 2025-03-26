package com.example.ecommerce.repoTest;


import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItems;
import com.example.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartItemDAO extends CrudRepository<CartItems, Integer> {

    List<CartItems> getCartItemsByCart(Cart cart);

    CartItems getCartItemsByCartAndProduct(Cart cart, Product product);

    @Modifying
    @Transactional
    @Query("delete from CartItems c where c.id =:id ")
    void deleteCartItemsById(@Param("id") long id);

}
