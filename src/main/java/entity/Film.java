package entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;

@Getter
@Setter


@Entity
@Table(name = "film", schema = "movie")
public class Film {

    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short film_id;

    @Column(name = "title")
    private String title;

    @Column(columnDefinition = "text")
    @Type(type = "text")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration")
    private Byte rentalDuration;

    @Column(name = "rental_rate")
    private BigDecimal rental_rate;

    @Column(name = "length")
    private Byte length;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;

    //@Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    public Rating rating;

    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
