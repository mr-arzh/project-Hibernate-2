package com.javahibernateapp.dao;
import com.javahibernateapp.entity.Rental;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class RentalDAO extends GenericDAO<Rental> {
    public RentalDAO(SessionFactory sessionFactory){
        super(Rental.class, sessionFactory);
    }

    public Rental getUnreturnedRentals(){
        Query<Rental> query = getCurrentSession().createQuery("select r from Rental r where r.returnDate is null", Rental.class);
        query.setMaxResults(1);
        return query.uniqueResult();
    }

    /*public Rental getAvailableRentals() {
        Query<Rental> query = getCurrentSession().createQuery("select r from Rental r where r.inventory_id = 0 or r.returnDate is not null", Rental.class);
        query.setMaxResults(1);
        return query.uniqueResult();
    }*/
}
