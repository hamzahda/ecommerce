package com.user.service;

import java.util.List;

import com.user.entity.User;
import com.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(final User user) {
        return userRepository.save(user);
    }

    public List<User> getUser() {
        return (List<User>) userRepository.findAll();
    }
    public boolean checkUser(long id){
        return userRepository.existsById(id);
    }

    public User findById(final long id) {
        return userRepository.findById(id)
            .orElse(null);
    }

    public User update(final User modifiedUser) {
        User toModifyUser = userRepository.findById(modifiedUser.getId())
            .orElse(null);
        toModifyUser.setAddress(modifiedUser.getAddress());
        toModifyUser.setName(modifiedUser.getName());
        return userRepository.save(toModifyUser);
    }

    public void deleteUserById(final long id) {
       userRepository.deleteById(id);
    }

           
	public void delete(String username) {
		userRepository.deleteByUsername(username);
	}

	public User search(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}
}