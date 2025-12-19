package ba.sum.fpmoz.filmotekabaza.controller;

import ba.sum.fpmoz.filmotekabaza.models.Actor;
import ba.sum.fpmoz.filmotekabaza.models.ActorMovie;
import ba.sum.fpmoz.filmotekabaza.models.ActorMovieId;
import ba.sum.fpmoz.filmotekabaza.models.Movie;
import ba.sum.fpmoz.filmotekabaza.repositories.ActorMovieRepository;
import ba.sum.fpmoz.filmotekabaza.repositories.ActorRepository;
import ba.sum.fpmoz.filmotekabaza.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
class MovieController {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private ActorMovieRepository actorMovieRepository;

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @PostMapping("/{movieId}/actors/{actorId}")
    public void addActorToMovie(@PathVariable Integer movieId, @PathVariable Integer actorId) {

        Actor actor = actorRepository.findById(actorId).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();

        ActorMovie link = new ActorMovie();
        ActorMovieId id = new ActorMovieId();
        id.setActorId(actorId);
        id.setMovieId(movieId);

        link.setId(id);
        link.setActor(actor);
        link.setMovie(movie);

        actorMovieRepository.save(link);
    }


    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Integer id) {
        return movieRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Integer id,
                             @RequestBody Movie movie) {
        Optional<Movie> dbMovie = movieRepository.findById(id);
        if (dbMovie.isPresent()) {
            Movie existingMovie = dbMovie.get();
            existingMovie.setTitle(movie.getTitle());
            existingMovie.setCategory(movie.getCategory());
            return movieRepository.save(existingMovie);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Integer id) {
        movieRepository.deleteById(id);
    }
}
