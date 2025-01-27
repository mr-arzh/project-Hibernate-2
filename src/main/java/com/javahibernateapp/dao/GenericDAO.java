package com.javahibernateapp.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;


import java.util.List;

public abstract class GenericDAO<T > {
    private final Class<T> classEntity;
    private SessionFactory sessionFactory;

    public GenericDAO(final Class<T> classEntityToSet, SessionFactory sessionFactory)   {
        this.classEntity = classEntityToSet;
        this.sessionFactory = sessionFactory;
    }

    public T getById(final long id) {
        return (T) getCurrentSession().get(classEntity, id);
    }

    public List<T> getItems(int from, int count) {
        Query query = getCurrentSession().createQuery("from " + classEntity.getName(), classEntity);
        query.setFirstResult(from);
        query.setMaxResults(count);
        return query.getResultList();
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + classEntity.getName(), classEntity).list();
    }

    public T create(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = getById(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}