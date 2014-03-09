package com.mantismonitor.service;

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

import com.mantismonitor.entity.Mantis;
import com.mantismonitor.entity.MantisGroup;
import com.mantismonitor.entity.User;
import com.mantismonitor.repository.MantisRepository;

public class MantisServiceTest {

	private MantisService mantisService;
	
	@Mock
	private MantisConnectPortType mantisConnect;
	
	@Mock
	private MantisRepository mantisRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mantisService = new MantisService(mantisConnect, mantisRepository);
		
		when(mantisRepository.save(any(Mantis.class))).then(new Answer<Mantis>() {

			@Override
			public Mantis answer(InvocationOnMock invocation) throws Throwable {
				Mantis mantis = (Mantis) invocation.getArguments()[0];
				return mantis;
			}
		});
	}
	
	@Test
	public void shouldAdd() {
		Mantis mantis = mantisService.add(1234L);
		assertTrue("should be 1234L", mantis.getId() == 1234L);
		
		mantis = mantisService.add(null);
		assertNull(mantis);
	}

	@Test
	public void shouldAddAndAssociateToMantisGroup() {
		MantisGroup mantisGroup = new MantisGroup();
		mantisGroup.setId(1L);
		mantisGroup.setName("Mantis Group 1");
		mantisGroup.setUser(new User("rafael.basics"));
	}
	
}
