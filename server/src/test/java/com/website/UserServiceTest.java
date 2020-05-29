package com.website;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.website.model.User;
import com.website.service.UserService;

import com.website.repository.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/* 
Author hamzahda

*/




@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository UserRepository;
    

    @Test
    public void getUserTest(){
        User mockUser = mock(User.class);
        long id = 1;
        
        when(UserRepository.findById(id)).thenReturn(Optional.of(mockUser));
        assertEquals(mockUser, userService.findById(id));  
    }

    @Test
    public void getUsersTest(){
        List<User> users = new ArrayList<>();
        User user = mock(User.class);
        
        users.add(user);
        when(user.getId()).thenReturn(new Long(1));
		when(user.getUsername()).thenReturn("name");
        when(UserRepository.findAll()).thenReturn(users);
        
        List<User> retrievedUsers = userService.getUser();
        
		assertEquals(retrievedUsers.size(), users.size());
        assertEquals(retrievedUsers.get(0), users.get(0));
    }

    @Test
    public void createUserTest(){
        User user = mock(User.class);

        when(user.getId()).thenReturn(new Long(1));
		when(user.getUsername()).thenReturn("name");
		when(UserRepository.save(user)).thenReturn(user);
		assertEquals(user, userService.createUser(user));
    }
    @Test
    public void modifUserTest(){
        User mockUser = mock(User.class);
        User userData = mock(User.class);

        when(mockUser.getId()).thenReturn((long) 1);
        when(mockUser.getEmail()).thenReturn("user\'s adress");
        when(mockUser.getUsername()).thenReturn("name");        

        when(UserRepository.save(mockUser)).thenReturn(mockUser);
        assertEquals(mockUser, userService.changeDetails(mockUser , userData));
    }



    @Test
    public void deleteUserTest(){
        User user = mock(User.class);
        userService.createUser(user);
        verify(UserRepository).save(user);    
    }

    @Test
	public void checkUser() {
		Long id = new Long(1);
		when(UserRepository.existsById(id)).thenReturn(true);
		assertTrue(userService.checkUser(id));
    }
    
    @Test
	public void search() {
        String name = new String("name");
        User user = mock(User.class);
		when(UserRepository.findByUsername(name)).thenReturn(user);
		assertTrue(userService.search(name) != null);
    }

}