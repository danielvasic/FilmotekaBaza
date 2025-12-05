package ba.sum.fpmoz.filmotekabaza.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categories", schema = "filmoteka")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;

    @OneToMany
    @JoinColumn(name = "category_id")
    private Set<Movie> movies = new LinkedHashSet<>();
}