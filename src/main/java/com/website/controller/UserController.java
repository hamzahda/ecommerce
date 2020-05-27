package com.website.controller;

package com.dev.controller;

import javax.servlet.http.HttpServletRequest;

import com.website.dto.TokenUserResponseDTO;
import com.website.dto.UserDataDTO;
import com.website.dto.UserLoginDTO;
import com.website.dto.UserResponseDTO;
import com.website.model.User;
import com.website.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  ModelMapper modelMapper;
  
  @Autowired
  private UserService userService;


  @GetMapping()
  public String getAll() {
    return userService.getAll();
  }

  @PutMapping("/{username}")
  public String changeDetails(@RequestBody User user) {
    return userService.changeDetails(user);
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