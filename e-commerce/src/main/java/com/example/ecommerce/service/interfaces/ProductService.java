package com.example.ecommerce.service.interfaces;


import com.example.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {

    void saveProduct(Product product);

    void deleteProduct(Product product);

    Product getProductById(int id);

    List<Product> getAllProductsByCategory(String category);

    List<Product> getAllProducts();

}
