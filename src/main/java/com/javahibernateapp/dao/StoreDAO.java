package com.javahibernateapp.dao;

import com.javahibernateapp.entity.Store;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class StoreDAO extends GenericDAO<Store> {

    public StoreDAO(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }

//    public Store getStores(int from, int count) {
//        Query<Store> query = getCurrentSession().createNativeQuery("select * from store", Store.class);
//        query.setFirstResult(from);
//        query.setMaxResults(count);
//        return query.getResultList().stream().findFirst().orElse(null);
//    }
}
