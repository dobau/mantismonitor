package com.mantismonitor.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

import com.mantismonitor.entity.User;
import com.mantismonitor.service.UserService;
import com.mantismonitor.util.UserSession;

public class HomeControllerTest {

	private HomeController homeController;
	private Result result;
	
	private UserSession userSession;
	
	@Mock
	private UserService userService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		result = new MockResult();
		userSession = new UserSession();
		
		homeController = new HomeController(userService, userSession, result);
	}
	
	@Test
	public void shouldLoginWithLoginAndPassword() throws Exception {
		when(userService.login(anyString(), anyString())).thenReturn(new User("rafael.basics"));
		
		homeController.login("rafael.basics", "brasil@123");
		
		assertTrue("should exists a success message", result.included().containsKey("success"));
		assertFalse("should not exists a error message", result.included().containsKey("error"));
		
		assertTrue("should user is logged", userSession.islogged());
	}

	@Test
	public void shouldNotLoginWithWrongPassword() throws Exception {
		when(userService.login(anyString(), anyString())).thenReturn(null);
		
		homeController.login("rafael.basics", "senhaerrada");
		
		assertFalse("should not exists a success message", result.included().containsKey("success"));
		assertTrue("should exists a error message", result.included().containsKey("error"));
		
		assertFalse("should user is not logged", userSession.islogged());
	}
	
	
	@Test
	public void shouldLogout() throws Exception {
		homeController.logout();

		assertTrue("should exists a notice message", result.included().containsKey("notice"));
		assertFalse("should not exists a error message", result.included().containsKey("error"));
		
		assertFalse("should user is not logged", userSession.islogged());
	}
	
}
