package ba.sum.fpmoz.filmotekabaza.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "movies", schema = "filmoteka")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"movies", "hibernateLazyInitializer", "handler"})
    private Category category;

    @ManyToMany(mappedBy = "movies")
    @JsonIgnoreProperties({"movies", "hibernateLazyInitializer", "handler"})
    private Set<Actor> actors;
}