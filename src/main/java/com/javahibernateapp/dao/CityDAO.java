package com.javahibernateapp.dao;
import com.javahibernateapp.entity.City;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CityDAO extends GenericDAO<City> {

    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public City getName(String name) {
        Query<City> query = getCurrentSession().createQuery("from City c where c.name = :name", City.class);
        query.setParameter("name", name);
        return query.uniqueResult();
    }
}
