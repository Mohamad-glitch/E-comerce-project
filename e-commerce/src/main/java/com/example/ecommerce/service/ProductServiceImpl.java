package com.example.ecommerce.service;

import com.example.ecommerce.DAO.interfaces.ProductDAO;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.interfaces.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Transactional
    @Override
    public void saveProduct(Product product) {
        productDAO.saveProduct(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Product product) {
        productDAO.deleteProduct(product);
    }

    @Transactional
    public List<Product> getAllProducts(){
        return productDAO.getAllProducts();
    }

    @Override
    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        return List.of();
    }
}
