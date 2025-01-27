package com.javahibernateapp.entity;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category", schema = "movie")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Byte id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_category",
    joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"),
    inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id") )
    private Set<Film> films = new HashSet<>();
}
