package com.javahibernateapp.dao;
import com.javahibernateapp.entity.Film;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class FilmDAO extends GenericDAO<Film> {
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film getFirstAvailableForRent() {
        Query<Film> query = getCurrentSession().createQuery("select f from Film f where f.id not in" +
                "(select film.id from Inventory )  ", Film.class);
        query.setMaxResults(1);
        return query.uniqueResult();
    }
}
