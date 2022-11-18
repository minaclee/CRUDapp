package minmin.collabera.Crudservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import minmin.collabera.Crudservice.exception.UserNotFoundException;
import minmin.collabera.Crudservice.model.Movie;
import minmin.collabera.Crudservice.repo.MovieRepository;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepo;

    @PostMapping("/movie")
    public Movie newMovie(@RequestBody Movie newMovie) {
        return movieRepo.save(newMovie);
    }

    @GetMapping("/movies")
    public List<Movie> gelAllMovies() {
        return movieRepo.findAll();
    }

    @GetMapping("/movie/{id}")
    Movie getMovieById(@PathVariable Long id) {
        return movieRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/movie/{id}")
    public Movie updateMovie(@RequestBody Movie newMovie, @PathVariable Long id) {
        return movieRepo.findById(id)
                .map(movie -> {
                    movie.setTitle(newMovie.getTitle());
                    movie.setDate(newMovie.getDate());
                    movie.setDirector(newMovie.getDirector());
                    return movieRepo.save(movie);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/movie/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (!movieRepo.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        movieRepo.deleteById(id);
        return "User with id " + id + " has been deleted success.";
    }

}
