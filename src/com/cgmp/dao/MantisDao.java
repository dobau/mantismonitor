package com.cgmp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.caelum.vraptor.ioc.Component;

import com.cgmp.entity.Mantis;

@Component
public class MantisDao {
	
	private EntityManager em;
	
	public MantisDao(EntityManager em) {
		this.em = em;
	}

	public List<Mantis> findMantisByGroup(String loginUser, String nameGroup) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Mantis> cMantis = cb.createQuery(Mantis.class);
		Root<Mantis> mantis = cMantis.from(Mantis.class);
		
		mantis.join("groupMantis").join("user");
		
		cMantis.where(cb.and(cb.equal(mantis.get("groupMantis.name"), nameGroup),
				cb.equal(mantis.get("groupMantis.user.login"), loginUser)));
		
		TypedQuery<Mantis> q = em.createQuery(cMantis);
		return q.getResultList();
	}

	public void save(Mantis mantis) {
		em.merge(mantis);
	}
	
}
