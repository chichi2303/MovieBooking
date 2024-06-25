package com.booking.bookingservice.controller;

import com.booking.bookingservice.model.Movie;
import com.booking.bookingservice.service.MovieService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/movies")
public class MovieController {

  private final MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping
  public ResponseEntity<List<Movie>> getAllMovies() {
    List<Movie> movies = movieService.getAllMovies();
    return new ResponseEntity<>(movies, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
    Optional<Movie> movie = movieService.getMovieById(id);
    return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
    Movie savedMovie = movieService.saveMovie(movie);
    return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
  }

  @PostMapping("/batch")
  public ResponseEntity<List<Movie>> createMovies(@RequestBody List<Movie> movies) {
    List<Movie> savedMovies = movieService.saveMovies(movies);
    return new ResponseEntity<>(savedMovies, HttpStatus.CREATED);
  }

  @PutMapping("/batch")
  public ResponseEntity<List<Movie>> updateMovies(@RequestBody List<Movie> movies) {
    List<Movie> updatedMovies = movieService.updateMovies(movies);
    return new ResponseEntity<>(updatedMovies, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
    movieService.deleteMovie(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
