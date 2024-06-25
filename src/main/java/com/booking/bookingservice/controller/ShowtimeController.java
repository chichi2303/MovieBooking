package com.booking.bookingservice.controller;

import com.booking.bookingservice.dto.ShowtimeRequest;
import com.booking.bookingservice.model.Showtime;
import com.booking.bookingservice.service.ShowtimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {

  private final ShowtimeService showtimeService;

  public ShowtimeController(ShowtimeService showtimeService) {
    this.showtimeService = showtimeService;
  }

  @GetMapping
  public ResponseEntity<List<Showtime>> getAllShowtimes() {
    List<Showtime> showtimes = showtimeService.getAllShowtimes();
    return new ResponseEntity<>(showtimes, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Showtime> getShowtimeById(@PathVariable Long id) {
    Showtime showtime = showtimeService.getShowtimebyId(id);
    return new ResponseEntity<>(showtime, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Showtime> addShowtime(@RequestBody ShowtimeRequest request) {
    Showtime showtime = showtimeService.addShowtime(
        request.getMovieID(), request.getAuditoriumId(), request.getTime(),
        request.getAvaliableSeats());
    return new ResponseEntity<>(showtime, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteShowtime(@PathVariable Long id) {
    showtimeService.deleteShowtime(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
