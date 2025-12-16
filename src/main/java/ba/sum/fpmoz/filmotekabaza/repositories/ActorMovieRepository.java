package ba.sum.fpmoz.filmotekabaza.repositories;

import ba.sum.fpmoz.filmotekabaza.models.ActorMovie;
import ba.sum.fpmoz.filmotekabaza.models.ActorMovieId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorMovieRepository extends JpaRepository<ActorMovie, ActorMovieId> {
}
