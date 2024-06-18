package com.booking.bookingservice.service;

import com.booking.bookingservice.model.Movie;
import com.booking.bookingservice.model.Showtime;
import com.booking.bookingservice.repository.MovieRepository;
import jakarta.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

  private final MovieRepository movieRepository;

  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }

  public Optional<Movie> getMovieById(Long id) {
    return movieRepository.findById(id);
  }

  public Movie saveMovie(Movie movie) {
    movie.getShowtimes().forEach(showtime -> showtime.setMovie(movie));
    return movieRepository.save(movie);
  }

  public List<Movie> saveMovies(List<Movie> movies) {
    movies.forEach(movie -> movie.getShowtimes().forEach(showtime -> showtime.setMovie(movie)));
    return movieRepository.saveAll(movies);
  }

  @Transactional
  public List<Movie> updateMovies(List<Movie> movies) {
    for (Movie movie : movies) {
      Movie existingMovie = movieRepository.findById(movie.getId())
          .orElseThrow(() -> new RuntimeException("Movie not found"));
      existingMovie.setTitle(movie.getTitle());
      existingMovie.setGenre(movie.getGenre());
      existingMovie.setDirector(movie.getDirector());

      // Update showtimes
      existingMovie.getShowtimes().clear();
      movie.getShowtimes().forEach(showtime -> showtime.setMovie(existingMovie));
      existingMovie.getShowtimes().addAll(movie.getShowtimes());
    }
    return movieRepository.saveAll(movies);
  }


  public void deleteMovie(Long id) {
    movieRepository.deleteById(id);
  }

}
