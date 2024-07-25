package com.booking.bookingservice.controller;

import com.booking.bookingservice.dto.MovieRequestDetail;
import com.booking.bookingservice.dto.response.MovieDetail;
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
  public ResponseEntity<List<MovieDetail>> getAllMovies() {
    List<MovieDetail> allmovies = movieService.getAllMovies();
    return new ResponseEntity<>(allmovies, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MovieDetail> getMovieById(@PathVariable Long id) {
    Optional<MovieDetail> movie = movieService.getMovieById(id);
    return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<MovieDetail> createMovie(
      @RequestBody MovieRequestDetail movieRequestDetail) {
    MovieDetail savedMovie = movieService.saveMovie(movieRequestDetail);
    return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
  }

  @PostMapping("/batch")
  public ResponseEntity<List<MovieDetail>> createMovies(
      @RequestBody List<MovieRequestDetail> movieRequestDetails) {
    List<MovieDetail> savedMovies = movieService.saveMovies(movieRequestDetails);
    return new ResponseEntity<>(savedMovies, HttpStatus.CREATED);
  }

  @PutMapping("/batch")
  public ResponseEntity<List<MovieDetail>> updateMovies(
      @RequestBody List<MovieRequestDetail> movieRequestDetails) {
    List<MovieDetail> updatedMovies = movieService.updateMovies(movieRequestDetails);
    return new ResponseEntity<>(updatedMovies, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
    movieService.deleteMovie(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
