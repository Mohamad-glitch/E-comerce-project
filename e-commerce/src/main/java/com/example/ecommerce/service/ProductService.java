package com.example.ecommerce.service;


import com.example.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {

    void saveProduct(Product product);

    List<Product> getAllProducts();

}
