package com.tafarelmello.webservice.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDAO<T, I extends Serializable> {

	@PersistenceContext(unitName = "webservicePU")
	protected EntityManager entityManager;

	private Class<T> persistedClass;

	protected GenericDAO() {
	}

	protected GenericDAO(Class<T> persistedClass) {
		this();
		this.persistedClass = persistedClass;
	}

	public void salvar(T entity) {
		entityManager.persist(entity);
	}

	public void atualizar(T entity) {
		entityManager.merge(entity);
	}

	public T encontrar(I id) {
		return entityManager.find(persistedClass, id);
	}

	public void remover(I id) {
		T entity = encontrar(id);
		entityManager.remove(entity);
	}

	public List<T> getList() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(persistedClass);
		query.from(persistedClass);
		return entityManager.createQuery(query).getResultList();
	}

}
