package com.example.ecommerce.DAO;

import com.example.ecommerce.entity.Product;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private final EntityManager entityManager;

    @Autowired
    public ProductDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        return List.of();
    }
}
