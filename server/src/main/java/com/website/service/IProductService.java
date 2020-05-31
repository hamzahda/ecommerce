package com.website.service;

import java.util.List;

import com.website.model.Product;

public interface IProductService {
    public Product createProduct(Product Product);
    public List<Product> getAll();
    public void delete(String Productname);
    public boolean search(String Productname);
    Product changeDetails(Product Product, Product ProductData);
    public Product getProduct(String Productname);
}