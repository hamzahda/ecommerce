package com.website.repository;

import javax.transaction.Transactional;

import com.website.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByUsername(String username);
  User findByUsername(String username);

  @Transactional
  void deleteByUsername(String username);

}