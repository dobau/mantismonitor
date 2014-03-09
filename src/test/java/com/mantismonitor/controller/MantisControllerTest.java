package com.mantismonitor.controller;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;

import com.mantismonitor.util.UserSession;

public class MantisControllerTest {
	
	private Result result;
	private UserSession userSession;
	
	private MantisController mantisController;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		result = new MockResult();
		userSession = new UserSession();
		
		//mantisController = new MantisController(userService, userSession, result);
	}

}
