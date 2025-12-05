package ba.sum.fpmoz.filmotekabaza.controller;

import ba.sum.fpmoz.filmotekabaza.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/movies")
class MovieController {
    @Autowired
    MovieRepository movieRepository;
}
