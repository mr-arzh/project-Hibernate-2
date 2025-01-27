package com.javahibernateapp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actor", schema = "movie")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Short id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDate lastUpdate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_actor",
    joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"),
    inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id") )
    private Set<Film> films = new HashSet<>();

}
