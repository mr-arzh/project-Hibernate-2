package com.javahibernateapp.dao;
import com.javahibernateapp.entity.Rental;
import org.hibernate.SessionFactory;

public class RentalDAO extends GenericDAO<Rental> {
    public RentalDAO(SessionFactory sessionFactory){
        super(Rental.class, sessionFactory);
    }
}
