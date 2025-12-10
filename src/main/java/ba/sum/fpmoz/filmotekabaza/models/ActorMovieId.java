package ba.sum.fpmoz.filmotekabaza.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ActorMovieId implements Serializable {
    private static final long serialVersionUID = -3864495003235089374L;
    @Column(name = "actor_id", nullable = false)
    private Integer actorId;

    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ActorMovieId entity = (ActorMovieId) o;
        return Objects.equals(this.actorId, entity.actorId) &&
                Objects.equals(this.movieId, entity.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, movieId);
    }

}