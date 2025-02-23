package com.example.ecommerce.service;

import com.example.ecommerce.DAO.ProductDAO;
import com.example.ecommerce.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
