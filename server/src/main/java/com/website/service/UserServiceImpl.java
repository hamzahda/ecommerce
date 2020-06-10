package com.website.service;

import java.util.List;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import com.website.dto.TokenUserResponseDTO;
import com.website.exception.CustomException;
import com.website.model.User;
import com.website.repository.UserRepository;
import com.website.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/* 
Author hamzahda

*/


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenUserResponseDTO signin(String username, String password) throws AuthenticationException {

        TokenUserResponseDTO tokenUserResponseDTO = new TokenUserResponseDTO();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        tokenUserResponseDTO
                .setToken(jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles()));
        return tokenUserResponseDTO;
        
    }
    
	public User signup(User user) {
		
		if (!userRepository.existsByUsername(user.getUsername())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return user;
		} else {
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

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

    public void deleteUserById(final long id) {
       userRepository.deleteById(id);
    }

	public void delete(String username) {
		userRepository.deleteByUsername(username);
	}

	public User search(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
		}
		return user;
	}
	public User changeDetails(User user, User userData) {
        user.setEmail(userData.getEmail());
        user.setRoles(userData.getRoles());
        user.setPassword(userData.getPassword());
        return userRepository.save(user);
    }

    public User whoami(HttpServletRequest req) {
		return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
	}

	public String refresh(String username) {
		return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
	}

}