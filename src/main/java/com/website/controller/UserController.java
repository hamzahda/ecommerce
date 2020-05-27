package com.website.controller;



import com.website.dto.UserResponseDTO;
import com.website.model.User;
import com.website.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  ModelMapper modelMapper;
  
  @Autowired
  private UserService userService;


  @GetMapping()
  public List<User> getAll() {
    return userService.getUser() ;
  }

  @PutMapping("/{username}")
  public User changeDetails(@PathVariable String name ) {
    return userService.changeDetails(name);
  }

  @DeleteMapping(value = "/{username}")
  public String delete( @PathVariable String username) {
    userService.delete(username);
    return username;
  }


  @GetMapping(value = "/{username}")
  public UserResponseDTO search(@PathVariable String username) {
    return modelMapper.map(userService.search(username), UserResponseDTO.class);
  }



}