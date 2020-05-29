package com.website;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.website.controller.UserController;
import com.website.model.User;
import com.website.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/* 
Author hamzahda

*/

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

	@InjectMocks
	UserController controller;

	@Mock
	UserService service;

	@Test
	public void getUser() {
		User mockUser = mock(User.class);
		Long id = new Long(1);

		when(mockUser.getId()).thenReturn(id);
        when(mockUser.getUsername()).thenReturn("name");
        when(service.checkUser(id)).thenReturn(true);
        when(service.findById(id)).thenReturn(mockUser);
        
		ResponseEntity<User> responseEntity = controller.getUser(id);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockUser, responseEntity.getBody());
	}

	@Test
	public void checkUser() {
        Long id = new Long(1);
        
        when(service.checkUser(id)).thenReturn(false);
        
		ResponseEntity<User> responseEntity = controller.getUser(id);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		assertEquals(null, responseEntity.getBody());
	}

	@Test
	public void getUsers() {
		List<User> accounts = new ArrayList<>();
		User mockAccount = mock(User.class);
		accounts.add(mockAccount);

		when(mockAccount.getId()).thenReturn(new Long(1));
		when(mockAccount.getUsername()).thenReturn("name");
		when(service.getUser()).thenReturn(accounts);

		ResponseEntity<List<User>> responseEntity = controller.getAll();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(accounts.size(), responseEntity.getBody().size());
		assertEquals(accounts.get(0), responseEntity.getBody().get(0));
	}

	@Test
	public void createUserReturnOK() {
		User mockUser= mock(User.class);
		Long id = new Long(1);

		when(mockUser.getId()).thenReturn(id);
		when(mockUser.getUsername()).thenReturn("name");
		when(service.createUser(mockUser)).thenReturn(mockUser);

		ResponseEntity<User> responseEntity = controller.postUser(mockUser);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(mockUser, responseEntity.getBody());
	}

	@Test
	public void modifyUserReturnOK() {
		User mockUser = mock(User.class);
		User userData = mock(User.class);

		when(mockUser.getId()).thenReturn(1L);
		when(mockUser.getUsername()).thenReturn("name");
		when(service.changeDetails(mockUser , userData)).thenReturn(mockUser);
		when(service.search("name")).thenReturn(mockUser);

		ResponseEntity<User> responseEntity = controller.modifyUser(mockUser, "name");
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockUser, responseEntity.getBody());
	}

	@Test
	public void modifyUserReturnNotFound() {
		when(service.search("name")).thenReturn(null);


		ResponseEntity<User> responseEntity = controller.modifyUser(null , "name");
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		assertEquals(null, responseEntity.getBody());
	}

	@Test
	public void deleteUserReturnOK() {
		Long id = new Long(1);

		when(service.checkUser(id)).thenReturn(true);

		ResponseEntity<String> responseEntity = controller.deleteUser(id);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}


	@Test
	public void deleteUserRetrunNotFound() {
		Long id = new Long(1);

		when(service.checkUser(id)).thenReturn(false);

		ResponseEntity<String> responseEntity = controller.deleteUser(id);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	

}
