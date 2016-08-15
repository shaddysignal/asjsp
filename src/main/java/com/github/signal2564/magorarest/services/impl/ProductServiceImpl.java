package com.github.signal2564.magorarest.services.impl;

import com.github.signal2564.magorarest.dao.ProductRepository;
import com.github.signal2564.magorarest.models.Product;
import com.github.signal2564.magorarest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> list() {
        return productRepository.findAll();
    }

    @Override
    public Product get(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }
}
