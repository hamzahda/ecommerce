package com.website.model;


import org.springframework.security.core.GrantedAuthority;
/* 
Author hamzahda

*/


public enum Role implements GrantedAuthority {
  ROLE_ADMIN, 
  ROLE_CLIENT;

  @Override
public String getAuthority() {
    return name();
  }

}
