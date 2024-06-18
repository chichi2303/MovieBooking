package com.booking.bookingservice.controller;

import com.booking.bookingservice.model.Movie;
import com.booking.bookingservice.service.MovieService;
import java.util.List;
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
  public List<Movie> getAllMovies() {
    return movieService.getAllMovies();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
    return movieService.getMovieById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Movie createMovie(@RequestBody Movie movie) {
    return movieService.saveMovie(movie);
  }

  @PostMapping("/batch")
  public List<Movie> createMovies(@RequestBody List<Movie> movies) {
    return movieService.saveMovies(movies);
  }

  @PutMapping("/batch")
  public List<Movie> updateMovies(@RequestBody List<Movie> movies) {
    return movieService.updateMovies(movies);
  }

  @DeleteMapping("/{id}")
  public void deleteMovie(@PathVariable Long id) {
    movieService.deleteMovie(id);
  }
}
