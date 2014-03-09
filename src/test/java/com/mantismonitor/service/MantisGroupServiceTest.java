package com.mantismonitor.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import biz.futureware.mantisconnect.MantisConnectPortType;

import com.mantismonitor.entity.MantisGroup;
import com.mantismonitor.entity.User;
import com.mantismonitor.exception.MantisGroupDontFoundException;
import com.mantismonitor.repository.MantisGroupRepository;
import com.mantismonitor.repository.MantisRepository;

public class MantisGroupServiceTest {

	private MantisGroupService mantisGroupService;
	
	private MantisService mantisService;
	
	@Mock
	private MantisGroupRepository mantisGroupRepository;
	
	@Mock
	private MantisRepository mantisRepository;
	
	@Mock
	private MantisConnectPortType mantisConnect;
	
	private Long sequenceId = 1L;
	public List<MantisGroup> mantisGroupList = new ArrayList<MantisGroup>();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		this.mantisService = new MantisService(mantisConnect, mantisRepository);
		this.mantisGroupService = new MantisGroupService(mantisGroupRepository, mantisService);
		
		User user = new User("rafael.basics");
		
		mantisGroupList.clear();
		
		MantisGroup mantisGroup1 = new MantisGroup();
		mantisGroup1.setId(sequenceId++);
		mantisGroup1.setUser(user);
		mantisGroup1.setName("Grupo 1");
		
		MantisGroup mantisGroup2 = new MantisGroup();
		mantisGroup2.setId(sequenceId++);
		mantisGroup2.setUser(user);
		mantisGroup2.setName("Grupo 2");
		
		mantisGroupList.add(mantisGroup1);
		mantisGroupList.add(mantisGroup2);
		
		when(mantisGroupRepository.findByUser(user)).thenReturn(mantisGroupList);
		when(mantisGroupRepository.save(any(MantisGroup.class))).thenAnswer(new Answer<MantisGroup>() {

			@Override
			public MantisGroup answer(InvocationOnMock invocation) throws Throwable {
				MantisGroup mantisGroup = (MantisGroup) invocation.getArguments()[0];
				mantisGroup.setId(sequenceId++);
				
				return mantisGroup;
			}
		});

		
		final List<MantisGroup> m = mantisGroupList;
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Long id = (Long)invocation.getArguments()[0];
				
				MantisGroup mantisGroup = new MantisGroup();
				mantisGroup.setId(id);
				
				m.remove(mantisGroup);
				
				return null;
			}
		}).when(mantisGroupRepository).delete(anyLong());
		
		when(mantisGroupRepository.findOne(anyLong())).then(new Answer<MantisGroup>() {

			@Override
			public MantisGroup answer(InvocationOnMock invocation) throws Throwable {
				Long id = (Long)invocation.getArguments()[0];

				MantisGroup mantisGroup = new MantisGroup();
				mantisGroup.setId(id);
				
				return mantisGroup;
			}
		});
	}
	
	@Test
	public void shouldGetAll() {
		List<MantisGroup> mantisGroupList = mantisGroupService.getAll(new User("rafael.basics"));
		
		assertTrue("should is size 2", mantisGroupList.size() == 2);
	}
	
	@Test
	public void shouldAdd() {
		User user = new User("rafael.basics");
		
		MantisGroup mantisGroup3 = new MantisGroup();
		mantisGroup3.setName("Grupo 3");
		mantisGroup3.setUser(user);
		
		MantisGroup mantisGroup = mantisGroupService.add(mantisGroup3);
		
		assertNotNull("should is not null", mantisGroup.getId());
		assertTrue("should is "+(sequenceId - 1), (sequenceId - 1) == mantisGroup.getId());
	}
	
	@Test
	public void shouldRemoveWithSuccess() {
		mantisGroupService.remove(1L);
		
		assertEquals(1, mantisGroupList.size());
	}
	
	@Test
	public void shouldGetOne() {
		MantisGroup mantisGroup = mantisGroupService.get(1L);
		
		assertTrue("should get one", 1L == mantisGroup.getId());
	}
	
	@Test(expected=MantisGroupDontFoundException.class)
	public void shouldVerifySuccess() throws MantisGroupDontFoundException {
		mantisGroupService.verify(new User("rafael.basics"), 5L);
	}
	
}
