package com.website.repository;

import javax.transaction.Transactional;

import com.website.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* 
Author hamzahda

*/


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByUsername(String username);
  User findByUsername(String username);

  @Transactional
  void deleteByUsername(String username);

}