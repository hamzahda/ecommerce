package com.user.service;

import java.util.List;

import com.user.entity.User;

public interface IUserService {
    public User createUser(User user);
    public List<User> getUser();
    public User findById(long id);
    public User update(User user);
    public void deleteUserById(long id);
    public boolean checkUser(long id);
}