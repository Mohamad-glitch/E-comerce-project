package com.example.ecommerce.repoTest;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItems;
import com.example.ecommerce.entity.Product;

public interface CartItemDAO{

    void saveItemQuantity(Long quantity);

    void saveCartItems(CartItems cartItems);

}
