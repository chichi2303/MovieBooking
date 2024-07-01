package com.booking.bookingservice.service;

import com.booking.bookingservice.model.Auditorium;
import com.booking.bookingservice.model.Movie;
import com.booking.bookingservice.model.Showtime;
import com.booking.bookingservice.repository.AuditoriumRepository;
import com.booking.bookingservice.repository.MovieRepository;
import com.booking.bookingservice.repository.ShowtimeRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShowtimeService {

  private final ShowtimeRepository showtimeRepository;
  private final MovieRepository movieRepository;
  private final AuditoriumRepository auditoriumRepository;

  // TODO: using lombok @RequireArgsConstructor
  public ShowtimeService(ShowtimeRepository showtimeRepository, MovieRepository movieRepository,
      AuditoriumRepository auditoriumRepository) {
    this.showtimeRepository = showtimeRepository;
    this.movieRepository = movieRepository;
    this.auditoriumRepository = auditoriumRepository;
  }

  public List<Showtime> getAllShowtimes() {
    return showtimeRepository.findAll();
  }

  public Showtime getShowtimebyId(Long id) {
    return showtimeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Showtime not found"));
  }

  public Showtime addShowtime(Long movieId, Long auditoriumId, LocalDateTime dateTime,
      int availableSeats) {
    Movie movie = movieRepository.findById(movieId)
        .orElseThrow(() -> new RuntimeException("Movie not found"));

    Auditorium auditorium = auditoriumRepository.findById(auditoriumId)
        .orElseThrow(() -> new RuntimeException("Auditorium not found"));

    if (showtimeRepository.existsByMovieIdAndAuditoriumIdAndTime(movieId, auditoriumId, dateTime)) {
      throw new RuntimeException("Showtime already exists for this movie and auditorium.");
    }

    Showtime showtime = Showtime.builder()
        .movie(movie)
        .auditorium(auditorium)
        .startTime(dateTime)
        .availableSeats(availableSeats)
        .build();

    return showtimeRepository.save(showtime);

  }

  public void deleteShowtime(Long id) {
    Showtime showtime = showtimeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Showtime not found"));
    if (!showtime.getBookings().isEmpty()) {
      throw new RuntimeException("Cannot remove showtime with active bookings.");
    }
    showtimeRepository.deleteById(id);
  }
}
