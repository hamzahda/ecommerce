package com.website.service;

import java.util.List;

import com.website.model.Product;
import com.website.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
             
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product changeDetails(Long id, Product productData) {
        Product product = productRepository.findById(id).orElse(null);
        product.setName(productData.getName());
        product.setImg_link(productData.getImg_link());
        product.setPrice(productData.getPrice());
        product.setStock(productData.getStock());
        return productRepository.save(product);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public boolean checkProduct(Long id) {
        return productRepository.existsById(id);
    }
/*
    @Override
    public Product search(String name) {
        return productRepository.findByProductname(name);
    }
*/

}