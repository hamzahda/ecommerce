package com.website.service;

import java.util.List;

import com.website.model.Product;

public interface IProductService {
    public Product createProduct(Product product);
    public List<Product> getAll();
    public void delete(Long id);
    Product changeDetails(Long id, Product ProductData);
    public Product getProduct(Long id);
    public boolean checkProduct(Long id);
    //public Product search(String name);
}

