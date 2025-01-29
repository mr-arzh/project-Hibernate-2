package com.javahibernateapp.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;


import java.util.List;


public abstract class GenericDAO<T> {
    private final Class<T> clazz;
    private SessionFactory sessionFactory;

    public GenericDAO(final Class<T> clazzToSet, SessionFactory sessionFactory) {
        this.clazz = clazzToSet;
        this.sessionFactory = sessionFactory;
    }

    public T getById(final int id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> getItems(int offset, int count) {
        Query<T> query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        return query.getResultList();
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName(), clazz).list();
    }

    public T save(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(final int entityId) {
        final T entity = getById(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}


/*
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
        System.out.println("Fetching items for entity: " + classEntity.getName());
        Query<T> query = getCurrentSession().createQuery("from " + classEntity.getName(), classEntity);
        query.setFirstResult(from);
        query.setMaxResults(count);

        List<T> result = query.getResultList();
        System.out.println("Fetched " + result.size() + " items");
        return result;
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

    public T save(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }
}*/
