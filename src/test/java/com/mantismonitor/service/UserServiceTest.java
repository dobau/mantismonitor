package com.mantismonitor.service;

import static com.mantismonitor.util.MatcherUtil.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import biz.futureware.mantisconnect.MantisConnectPortType;
import biz.futureware.mantisconnect.ObjectRef;

import com.mantismonitor.entity.User;
import com.mantismonitor.repository.UserRepository;

public class UserServiceTest {

	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private MantisConnectPortType mantisConnect;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userService = new UserService(mantisConnect, userRepository);
		
		when(mantisConnect.mc_enum_access_levels(argThat(isNotEmptyStringMatcher), argThat(isNotEmptyStringMatcher))).thenReturn(new ObjectRef[1]);
		
		when(userRepository.save(any(User.class))).thenAnswer(new Answer<User>() {
			public User answer(InvocationOnMock invocation) throws Throwable {
				return (User) invocation.getArguments()[0];
			}
		});
	}
	
	@Test
	public void shoudLoginWithLoginAndPassword() throws Exception {
		User userRafael = userService.login("rafael.basics", "12345678");

		assertNotNull(userRafael);
	}
	
	@Test
	public void shoudLoginWithoutLoginAndPassword() throws Exception {
		User userRafael;
		
		userRafael = userService.login("", "");
		assertNull(userRafael);
		userRafael = userService.login("", "senha");
		assertNull(userRafael);
		userRafael = userService.login("rafael.basics", "");
		assertNull(userRafael);

		userRafael = userService.login(null, null);
		assertNull(userRafael);
		userRafael = userService.login("rafael.basics", null);
		assertNull(userRafael);
		userRafael = userService.login(null, "senha");
		assertNull(userRafael);
	}
	
}
