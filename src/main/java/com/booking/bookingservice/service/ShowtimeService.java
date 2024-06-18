package com.booking.bookingservice.service;

import com.booking.bookingservice.model.Showtime;
import com.booking.bookingservice.repository.ShowtimeRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShowtimeService {

  private final ShowtimeRepository showtimeRepository;

  public ShowtimeService(ShowtimeRepository showtimeRepository) {
    this.showtimeRepository = showtimeRepository;
  }

  public List<Showtime> getAllShowtimes() {
    return showtimeRepository.findAll();
  }

  public Showtime getShowtimebyId(Long id) {
    return showtimeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Showtime not found"));
  }

  public Showtime saveShowtime(Showtime showtime) {
    return showtimeRepository.save(showtime);
  }

  public void deleteShowtime(Long id) {
    showtimeRepository.deleteById(id);
  }

}
