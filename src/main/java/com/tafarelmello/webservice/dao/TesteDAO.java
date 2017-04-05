package com.tafarelmello.webservice.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.tafarelmello.webservice.model.Teste;

@Stateless
public class TesteDAO extends GenericDAO<Teste, Long> {
	public TesteDAO() {
		super(Teste.class);
	}

	@SuppressWarnings("unchecked")
	public List<Teste> getListOrdenada() {
		Query query = entityManager.createQuery("SELECT t FROM Teste t ORDER BY t.id ASC");

		return query.getResultList();
	}

}
