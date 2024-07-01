package com.booking.bookingservice.service;

import com.booking.bookingservice.model.Movie;
import com.booking.bookingservice.repository.MovieRepository;
import com.booking.bookingservice.repository.ShowtimeRepository;
import jakarta.transaction.Transactional;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

  private final MovieRepository movieRepository;
  private final ShowtimeRepository showtimeRepository;

  public MovieService(MovieRepository movieRepository, ShowtimeRepository showtimeRepository) {
    this.movieRepository = movieRepository;
    this.showtimeRepository = showtimeRepository;
  }


  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }

  public Optional<Movie> getMovieById(Long id) {
    return movieRepository.findById(id);
  }

  public Movie saveMovie(Movie movie) {
    if (movieRepository.findByTitle(movie.getTitle()).isPresent()) {
      throw new RuntimeException("Movie is already exists");
    }
    movie.getShowtimes().forEach(showtime -> showtime.setMovie(movie));
    return movieRepository.save(movie);
  }

  public List<Movie> saveMovies(List<Movie> movies) {
    movies.forEach(movie -> {
      if (movieRepository.findByTitle(movie.getTitle()).isPresent()) {
        throw new RuntimeException("One or more movies already exist");
      }
      movie.getShowtimes().forEach(showtime -> showtime.setMovie(movie));
    });
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
    // TODO: optimize that query
    Movie movie = movieRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Movie not found"));
    if (!showtimeRepository.findByMovie(movie).isEmpty()) {
      throw new RuntimeException("Cannot delete movie with active showtimes");
    }
    movieRepository.deleteById(id);
  }
}
