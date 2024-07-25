package com.booking.bookingservice.service;

import com.booking.bookingservice.dto.ShowtimeRequestDetail;
import com.booking.bookingservice.dto.response.ShowtimeDetail;
import com.booking.bookingservice.mapper.ShowtimeMapper;
import com.booking.bookingservice.model.Auditorium;
import com.booking.bookingservice.model.Movie;
import com.booking.bookingservice.model.Showtime;
import com.booking.bookingservice.repository.ShowtimeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShowtimeService {

  private final ShowtimeRepository showtimeRepository;

  public List<ShowtimeDetail> getAllShowtimes() {
    List<Showtime> showtimes = showtimeRepository.findAll();
    return showtimes.stream()
        .map(ShowtimeMapper::mapToShowtimeDetail)
        .toList();
  }

  public ShowtimeDetail getShowtimebyId(Long id) {
    return showtimeRepository.findById(id)
        .map((ShowtimeMapper::mapToShowtimeDetail))
        .orElseThrow(() -> new RuntimeException("Showtime not found"));
  }

  public Showtime addShowtime(ShowtimeRequestDetail requestDetail) {
    // TODO: check performance - DONE

    if (showtimeRepository.existsByMovieIdAndAuditoriumIdAndTime(requestDetail.getMovieId(),
        requestDetail.getAuditoriumId(), requestDetail.getTime())) {
      throw new RuntimeException("Showtime already exists for this movie and auditorium.");
    }

    Movie movie = new Movie();
    movie.setId(requestDetail.getMovieId());

    Auditorium auditorium = new Auditorium();
    auditorium.setId(requestDetail.getAuditoriumId());

    Showtime showtime = Showtime.builder()
        .movie(movie)
        .auditorium(auditorium)
        .startTime(requestDetail.getTime())
        .availableSeats(requestDetail.getAvailableSeats())
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
