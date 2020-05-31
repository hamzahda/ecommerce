package com.website.service;

import java.util.List;

import com.website.model.Product;
import com.website.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;


    @Override
    public Product changeDetails(Product Product, Product ProductData) {
        return null;
    }

    @Override
    public Product createProduct(Product Product) {
        return productRepository.save(Product);
    }

    @Override
    public void delete(String Productname) {
         productRepository.deleteByProductname(Productname);
    }
    
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(String Productname) {
        return productRepository.findByProductname(Productname);
    }

    @Override
    public boolean search(String Productname) {
        return productRepository.existsByProductname(Productname);
    }

}