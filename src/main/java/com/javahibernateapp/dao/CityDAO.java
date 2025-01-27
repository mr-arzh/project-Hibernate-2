package com.javahibernateapp.dao;
import com.javahibernateapp.entity.City;
import org.hibernate.SessionFactory;

public class CityDAO extends GenericDAO<City> {

    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}
