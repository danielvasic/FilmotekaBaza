package ba.sum.fpmoz.filmotekabaza.controller;

import ba.sum.fpmoz.filmotekabaza.models.Movie;
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

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
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
