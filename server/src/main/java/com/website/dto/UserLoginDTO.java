package com.website.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserLoginDTO {

  @ApiModelProperty(position = 0)
  private String username;

  @ApiModelProperty(position = 1)
  String password;

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

  
 
}
