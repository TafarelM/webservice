package com.tafarelmello.webservice.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.tafarelmello.webservice.model.Teste;

@Stateless
public class TesteDAO extends GenericDAO<Teste, Long> {
	public TesteDAO() {
		super(Teste.class);
	}

	public List<Teste> getListOrdenada() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Teste> query = builder.createQuery(Teste.class);
		Root<Teste> from = query.from(Teste.class);
		query.select(from).orderBy(builder.asc(from.get("id")));
		List<Teste> lista = entityManager.createQuery(query).getResultList();
		return lista;
	}

}
