package com.website.controller;

import java.util.List;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import com.website.dto.TokenUserResponseDTO;
import com.website.dto.UserDataDTO;
import com.website.dto.UserLoginDTO;
import com.website.dto.UserResponseDTO;
import com.website.model.User;
import com.website.service.UserServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.*;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.*;

/* 
Author hamzahda

*/

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  private UserServiceImpl userService;
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);


  /*
    @return list of all the  users
  */
  @ApiOperation(value = "${UserController.getAll}")
  @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), 
    @ApiResponse(code = 403, message = "Access denied"), 
    @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  @GetMapping(value = "/")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<User>> getAll() {
    logger.info("GET: getAll");
    return ResponseEntity.ok(userService.getUser());
  }

  
  /*
    @return user by his id
  */
  @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), 
    @ApiResponse(code = 403, message = "Access denied"), 
    @ApiResponse(code = 404, message = "The user doesn't exist"),
    @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  @ApiOperation(value = "${UserController.getUsers(id)}")
  @GetMapping(value = "/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    if (!userService.checkUser(id)) {
      logger.info("GET: getUser");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(userService.findById(id));
  }



  
  /*
    search for a user
  */


  @ApiResponses(value = {
    @ApiResponse(code = 400, message = "Something went wrong"), 
    @ApiResponse(code = 403, message = "Access denied"), 
    @ApiResponse(code = 404, message = "The user doesn't exist"), 
    @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  @ApiOperation(value = "${UserController.search(name)}")
  @GetMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public UserResponseDTO search(@PathVariable String username) {
    logger.info("GET: search");
    return modelMapper.map(userService.search(username), UserResponseDTO.class);
  }
  
  
  
  /*
    delete user by his id
  */
  @ApiOperation(value = "${UserController.deleteUser(id)}")
  @ApiResponses(value = {
    @ApiResponse(code = 400, message = "Something went wrong"), 
    @ApiResponse(code = 403, message = "Access denied"), 
    @ApiResponse(code = 404, message = "The user doesn't exist"), 
    @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<String> deleteUser(@PathVariable final long id) {
    logger.info("DELETE: deleteUser");
    if (!userService.checkUser(id)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(String.format("User with ID : %d is deleted successfuly", id));
  }
  
  /*
    @return the modified user
  */ 
  @ApiOperation(value = "${UserController.modifyUser}")
  @ApiResponses(value = {
    @ApiResponse(code = 400, message = "Something went wrong"), 
    @ApiResponse(code = 403, message = "Access denied"),
    @ApiResponse(code = 404, message = "The user doesn't exist"), 
    @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  @PutMapping("/{name}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<User> modifyUser(@RequestBody final User userData, @PathVariable final String name) {
    logger.info("put: modifyUser");
    User user = userService.search(name);
    if (user != null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(userService.changeDetails(user, userData));
  }

  /*
    delete user given his name
  */


  @ApiOperation(value = "${UserController.delete}")
  @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), 
    @ApiResponse(code = 403, message = "Access denied"), 
    @ApiResponse(code = 404, message = "The user doesn't exist"), 
    @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  @DeleteMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<String> delete(@PathVariable String username) {
    logger.info("DELETE: deleteUser");
    User user = userService.search(username);
    if (user != null) {
      userService.delete(username);
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }


  /*
    @return TokenUserResponseDTO including the token
  */

  @ApiOperation(value = "${UserController.signin}")
  @ApiResponses( value = { 
      @ApiResponse(code = 400, message = "Something went wrong"),
      @ApiResponse(code = 422, message = "Invalid username/password supplied")
    })
  @PostMapping("/signin")
  public TokenUserResponseDTO login(@RequestBody UserLoginDTO user) throws AuthenticationException {
    logger.info("POST: login");
    return userService.signin(user.getUsername(), user.getPassword());
  }

  
  /*
    @return signed up user
  */
  @ApiOperation(value = "${UserController.signup}")
  @PostMapping("/signup")
  @ApiResponses(value = {
    @ApiResponse(code = 400, message = "Something went wrong"), 
    @ApiResponse(code = 403, message = "Access denied"), 
    @ApiResponse(code = 422, message = "Username is already in use"), 
    @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public User signup( @RequestBody UserDataDTO user) {
    logger.info("POST: register");
    return userService.signup(modelMapper.map(user, User.class));
  }
  
  /*
    @return logged in user
  */
  
  @ApiOperation(value = "${UserController.me}")
  @ApiResponses(value = {
    @ApiResponse(code = 400, message = "Something went wrong"), 
    @ApiResponse(code = 403, message = "Access denied"), 
    @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  @GetMapping(value = "/me")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public UserResponseDTO whoami(HttpServletRequest req) {
    logger.info("GET: whoami");
    return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
  }

  /*
    @return refreshed  token
  */
  @ApiOperation(value = "${UserController.refresh}")
  @GetMapping("/refresh")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")

  public String refresh(HttpServletRequest req) {
    logger.info("GET: refresh");
    return userService.refresh(req.getRemoteUser());
  }


  /*
    @return created user
  */
  @ApiOperation(value = "${UserController.postUser}")
  public ResponseEntity<User> postUser(User user) {
   logger.info("POST: postUser");
   user =  userService.createUser(user);
   return ResponseEntity.status(HttpStatus.CREATED).body(user);
}


}