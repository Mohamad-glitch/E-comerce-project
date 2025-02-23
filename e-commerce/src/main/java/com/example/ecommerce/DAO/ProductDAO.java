package com.example.ecommerce.DAO;

import com.example.ecommerce.entity.Product;


import java.util.List;

public interface ProductDAO {

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    Product getProductById(int id);

    List<Product> getAllProductsByCategory(String category);

}
