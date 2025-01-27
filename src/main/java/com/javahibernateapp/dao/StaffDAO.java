package com.javahibernateapp.dao;
import com.javahibernateapp.entity.Staff;
import org.hibernate.SessionFactory;

public class StaffDAO extends GenericDAO<Staff> {
    public StaffDAO(SessionFactory sessionFactory ){
        super(Staff.class, sessionFactory);
    }
}