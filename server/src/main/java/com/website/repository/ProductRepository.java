package com.website.repository;



import com.website.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* 
Author hamzahda

*/


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  //Product findByname(String name);
}