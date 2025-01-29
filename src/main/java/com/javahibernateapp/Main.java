package com.javahibernateapp;

import com.javahibernateapp.dao.*;
import com.javahibernateapp.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Main {

    private final SessionFactory sessionFactory;

    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final LanguageDAO languageDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;


    public Main() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/movie");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.use_sql_comments", "true");
        properties.put("hibernate.highlight_sql", "true");
        sessionFactory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .addProperties(properties)
                .buildSessionFactory();

        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
    }

    public static void main(String[] args) {

        Main main = new Main();
        Customer customer = main.createCustomer();

        //main.customerReturnInventoryToStore();

        //main.customerRentInventory(customer);

        main.newFilmAvailableForRent();




    }

    private void newFilmAvailableForRent() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Language language = languageDAO.getItems(0,5).stream().unordered().findAny().get();
            List<Category> categories = categoryDAO.getItems(0,5);
            List<Actor> actors = actorDAO.getItems(0,5);


            Film newFilm = new Film();
            newFilm.setActors(new HashSet<>(actors));
            newFilm.setLanguage(language);
            newFilm.setCategories(new HashSet<>(categories));
            newFilm.setDescription("Lalala Khueta");
            newFilm.setLength((short) 30);
            newFilm.setTitle("Terminananator");
            newFilm.setRental_rate(BigDecimal.valueOf(25.78));
            newFilm.setReplacementCost(BigDecimal.valueOf(33.89));
            newFilm.setLastUpdate(LocalDateTime.now());
            newFilm.setOriginalLanguage(language);
            newFilm.setRating(Rating.R);
            newFilm.setReleaseYear(Year.of(1995));
            newFilm.setFeature(Set.of(Features.COMMENTARIES, Features.TRAILERS));
            newFilm.setRentalDuration((byte) 30);
            filmDAO.save(newFilm);

            FilmText filmText = new FilmText();
            filmText.setFilm(newFilm);
            filmText.setDescription("Lalala Khueta");
            filmText.setTitle("Terminananator");
            filmText.setId(newFilm.getId());
            filmTextDAO.save(filmText);


            transaction.commit();
        }
    }

    private void customerRentInventory(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Film filmAvailableForRent = filmDAO.getFirstAvailableForRent();
            Store availableStore = storeDAO.getItems(0,1).get(0);

            Inventory inventory = new Inventory();
            inventory.setFilm(filmAvailableForRent);
            inventory.setStore(availableStore);
            inventoryDAO.save(inventory);

            Staff staff = availableStore.getStaff();

            Rental rental = new Rental();
            rental.setCustomer(customer);
            rental.setStaff(staff);
            rental.setInventory(inventory);
            rental.setLastUpdate(LocalDateTime.now());
            rentalDAO.save(rental);

            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setRental(rental);
            payment.setStaff(staff);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setAmount(filmAvailableForRent.getRental_rate());
            payment.setLastUpdate(LocalDateTime.now());
            paymentDAO.save(payment);

            transaction.commit();
        }
    }

    private void customerReturnInventoryToStore() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Rental unreternedRental = rentalDAO.getUnreturnedRentals();
            unreternedRental.setReturnDate(LocalDateTime.now()); //факт того что произошел возврат
            rentalDAO.save(unreternedRental);



            transaction.commit();
        }
    }

    private Customer createCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Store store = storeDAO.getItems(0,1).get(0);

            /*List<City> cities = cityDAO.getItems(0, 1);
            if (cities.isEmpty()) {
                throw new RuntimeException("No cities found in the database");
            }
            City city = cities.get(0);*/

            City city = cityDAO.getName("Jakarta");

            Address address = new Address();
            address.setAddress("Cumhuriet sk, 5");
            address.setDistrict("Kartal");
            address.setCity(city);
            address.setPhone("777-777-111");
            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setFirstName("Dmitrii");
            customer.setLastName("Arzh");
            customer.setEmail("siska@gmail.com");
            customer.setIsActive(true);
            customer.setStore(store);
            customer.setAddress(address);
            customerDAO.save(customer);

            transaction.commit();
            return customer;
        }
    }
}