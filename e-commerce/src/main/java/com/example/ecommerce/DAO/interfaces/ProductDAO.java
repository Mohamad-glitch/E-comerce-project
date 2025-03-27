package com.example.ecommerce.DAO.interfaces;

import com.example.ecommerce.entity.Product;


import java.util.List;

public interface ProductDAO {

    void saveProduct(Product product);

    void deleteProduct(Product product);

    Product getProductById(int id);

    List<Product> getAllProductsByCategory(String category);

    List<Product> getAllProducts();

}
