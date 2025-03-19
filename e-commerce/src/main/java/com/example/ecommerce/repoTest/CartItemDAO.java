package com.example.ecommerce.repoTest;


import com.example.ecommerce.entity.CartItems;


public interface CartItemDAO{

    void saveItemQuantity(Long quantity);

    void saveCartItems(CartItems cartItems);

}
