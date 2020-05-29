package com.website.controller;

import java.util.List;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import com.website.dto.TokenUserResponseDTO;
import com.website.dto.UserDataDTO;
import com.website.dto.UserLoginDTO;
import com.website.dto.UserResponseDTO;
import com.website.model.User;
import com.website.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  private UserService userService;

  @GetMapping(value = "/")
  public ResponseEntity<List<User>> getAll() {
    return ResponseEntity.ok(userService.getUser());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    if (!userService.checkUser(id)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(userService.findById(id));
  }

  @GetMapping(value = "/{username}")
  public UserResponseDTO search(@PathVariable String username) {
    return modelMapper.map(userService.search(username), UserResponseDTO.class);
  }

  @PostMapping("/")
  public ResponseEntity<User> postUser(@RequestBody final User user) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable final long id) {
    if (!userService.checkUser(id)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(String.format("User with ID : %d is deleted successfuly", id));
  }

  @PutMapping("/{name}")
  public ResponseEntity<User> modifyUser(@RequestBody final User userData, @PathVariable final String name) {
    User user = userService.search(name);
    if (user != null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(userService.changeDetails(user, userData));
  }

  @DeleteMapping(value = "/{username}")
  public ResponseEntity<String> delete(@PathVariable String username) {
    User user = userService.search(username);
    if (user != null) {
      userService.delete(username);
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }

  @PostMapping("/signin")
  public TokenUserResponseDTO login(@RequestBody UserLoginDTO user) throws AuthenticationException {
    return userService.signin(user.getUsername(), user.getPassword());
  }
  @PostMapping("/signup")
  public User signup( @RequestBody UserDataDTO user) {
    return userService.signup(modelMapper.map(user, User.class));
  }
  
  @GetMapping(value = "/me")
  public UserResponseDTO whoami(HttpServletRequest req) {
    return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
  }

  @GetMapping("/refresh")
  public String refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }
}