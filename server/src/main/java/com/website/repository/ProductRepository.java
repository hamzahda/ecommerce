package com.website.repository;

import javax.transaction.Transactional;

import com.website.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* 
Author hamzahda

*/


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  boolean existsByProductname(String Productname);
  Product findByProductname(String Productname);
  
  @Transactional
  void deleteByProductname(String Productname);

}