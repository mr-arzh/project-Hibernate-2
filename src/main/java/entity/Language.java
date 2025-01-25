package entity;
package entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;

@Getter
@Setter


@Entity
@Table(name = "language", schema = "movie")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short language_id;

    @Column(columnDefinition = "char", name = "name")
    @Type(type = "char")
    private String name;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

}
