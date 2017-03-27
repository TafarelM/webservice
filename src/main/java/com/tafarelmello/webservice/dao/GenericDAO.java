package com.tafarelmello.webservice.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.Valid;

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

	public T salvar(@Valid T entity) {
		entityManager.persist(entity);
		return entity;
	}

	public T atualizar(@Valid T entity) {
		entityManager.merge(entity);
		return entity;
	}

	public void remover(I id) {
		T entity = encontrar(id);

		T mergedEntity = entityManager.merge(entity);
		entityManager.remove(mergedEntity);
	}

	public List<T> getList() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(persistedClass);
		query.from(persistedClass);
		return entityManager.createQuery(query).getResultList();
	}

	public T encontrar(I id) {
		return entityManager.find(persistedClass, id);
	}

}
