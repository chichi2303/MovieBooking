package com.booking.bookingservice.service;

import com.booking.bookingservice.dto.MovieRequestDetail;
import com.booking.bookingservice.dto.response.MovieDetail;
import com.booking.bookingservice.mapper.MovieMapper;
import com.booking.bookingservice.model.Movie;
import com.booking.bookingservice.model.Showtime;
import com.booking.bookingservice.repository.MovieRepository;
import com.booking.bookingservice.repository.ShowtimeRepository;
import jakarta.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class MovieService {

  private final MovieRepository movieRepository;
  private final ShowtimeRepository showtimeRepository;


  public List<MovieDetail> getAllMovies() {
    List<Movie> movies = movieRepository.findAll();
    return movies.stream()
        .map(MovieMapper::mapToMovieDetail)
        .toList();
  }

  public Optional<MovieDetail> getMovieById(Long id) {
    return movieRepository.findById(id)
        .map(MovieMapper::mapToMovieDetail);
  }

  public MovieDetail saveMovie(MovieRequestDetail requestDetail) {
    // TODO: don't use entity - Done
    if (movieRepository.existsByMovieId(requestDetail.getId())) {
      throw new RuntimeException("Movie is already exists");
    }
    Movie movie = MovieMapper.mapToMovie(requestDetail);
    movie.getShowtimes().forEach(showtime -> showtime.setMovie(movie));
    Movie savedMovie = movieRepository.save(movie);
    return MovieMapper.mapToMovieDetail(savedMovie);
  }

  public List<MovieDetail> saveMovies(List<MovieRequestDetail> movieRequestDetails) {
    List<Movie> movies = movieRequestDetails.stream()
        .map(MovieMapper::mapToMovie)
        .toList();

    movies.forEach(movie -> {
      if (movieRepository.existsByMovieId(movie.getId())) {
        throw new RuntimeException("One or more movies already exist");
      }
      movie.getShowtimes().forEach(showtime -> showtime.setMovie(movie));
    });

    List<Movie> savedMovies = movieRepository.saveAll(movies);
    return savedMovies.stream()
        .map(MovieMapper::mapToMovieDetail)
        .toList();
  }


  @Transactional
  public List<MovieDetail> updateMovies(List<MovieRequestDetail> movieRequestDetails) {
    List<Movie> updatedMovies = new ArrayList<>();

    // list movieIds
    // query get all movies with given ids. map result to Map<Long, Movie> movieId -> movie
    for (MovieRequestDetail movieRequestDetail : movieRequestDetails) {
      Movie existingMovie = movieRepository.findById(movieRequestDetail.getId())
          .orElseThrow(() -> new RuntimeException("Movie not found"));
      existingMovie.setTitle(movieRequestDetail.getTitle());
      existingMovie.setGenre(movieRequestDetail.getGenre());
      existingMovie.setDirector(movieRequestDetail.getDirector());

      // Update showtimes
      existingMovie.getShowtimes().clear();
      existingMovie.getShowtimes().addAll(
          movieRequestDetail.getShowtimes().stream()
              .map(showtimeDetail -> {
                Showtime showtime = MovieMapper.toShowtime(showtimeDetail);
                showtime.setMovie(existingMovie);
                return showtime;
              })
              .toList()
      );

      updatedMovies.add(existingMovie);
    }
    List<Movie> savedMovies = movieRepository.saveAll(updatedMovies);
    return savedMovies.stream()
        .map(MovieMapper::mapToMovieDetail)
        .toList();
  }


  public void deleteMovie(Long id) {
    if (showtimeRepository.existsByMovieId(id)) {
      throw new RuntimeException("Cannot delete movie with active showtimes");
    }
    movieRepository.deleteById(id);
  }
}
