package com.booking.bookingservice.mapper;

import com.booking.bookingservice.dto.MovieRequestDetail;
import com.booking.bookingservice.dto.response.MovieDetail;
import com.booking.bookingservice.dto.response.ShowtimeDetail;
import com.booking.bookingservice.model.Movie;
import com.booking.bookingservice.model.Showtime;

public class MovieMapper {

  //Map from DB to DTO
  public static MovieDetail mapToMovieDetail(Movie movie) {
    return MovieDetail.builder()
        .id(movie.getId())
        .title(movie.getTitle())
        .genre(movie.getGenre())
        .director(movie.getDirector())
        .build();
  }

  //Map to DB
  public static Movie mapToMovie(MovieRequestDetail movieRequestDetail) {
    Movie movie = Movie.builder()
        .id(movieRequestDetail.getId())
        .title(movieRequestDetail.getTitle())
        .genre(movieRequestDetail.getGenre())
        .director(movieRequestDetail.getDirector())
        .build();
    if (movieRequestDetail.getShowtimes() != null) {
      movie.setShowtimes(movieRequestDetail.getShowtimes().stream()
          .map(MovieMapper::toShowtime)
          .toList());
    }
    return movie;
  }

  public static ShowtimeDetail mapToShowtimeDetail(Showtime showtime) {
    return ShowtimeDetail.builder()
        .id(showtime.getId())
        .startTime(showtime.getStartTime())
        .availableSeats(showtime.getAvailableSeats())
        .movieId(showtime.getMovie().getId())
        .auditoriumId(showtime.getAuditorium().getId())
        .build();
  }

  public static Showtime toShowtime(ShowtimeDetail showtimeDetail) {
    return Showtime.builder()
        .id(showtimeDetail.getId())
        .startTime(showtimeDetail.getStartTime())
        .availableSeats(showtimeDetail.getAvailableSeats())
        .build();
  }

}
