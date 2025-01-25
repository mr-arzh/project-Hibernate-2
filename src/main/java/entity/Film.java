package entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.HashSet;

@Getter
@Setter


@Entity
@Table(name = "film", schema = "movie")
public class Film {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "year")
    private Integer year;

    @Column(name = "rental_duration")
    private Integer rental_duration;

    @Column(name = "rental_rate")
    private Double rental_rate;

    @Column(name = "length")
    private Integer length;

    @Column(name = "replacement_cost")
    private Double replacement_cost;

    @Enumerated(EnumType.ORDINAL)
    public Enum rating;

    @Column(name = "special_features")
    private HashSet<String> special_features;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date last_update;
}
