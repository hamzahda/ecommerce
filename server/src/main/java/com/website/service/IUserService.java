package com.website.service;

import java.util.List;

import javax.naming.AuthenticationException;

import com.website.dto.TokenUserResponseDTO;
import com.website.model.User;
/* 
Author hamzahda

*/

public interface IUserService {
    public User createUser(User user);
    public List<User> getUser();
    public User findById(long id);
    User changeDetails(User user, User userData);
    public void deleteUserById(long id);


    public boolean checkUser(long id);
    public void delete(String username);
    public User search(String username);
    public TokenUserResponseDTO signin(String username, String password) throws AuthenticationException;
    public User signup(User user);
}