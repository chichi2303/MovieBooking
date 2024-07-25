package com.booking.bookingservice.controller;

import com.booking.bookingservice.dto.ShowtimeRequestDetail;
import com.booking.bookingservice.dto.response.ShowtimeDetail;
import com.booking.bookingservice.model.Showtime;
import com.booking.bookingservice.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

  private final ShowtimeService showtimeService;

  @GetMapping
  public ResponseEntity<List<ShowtimeDetail>> getAllShowtimes() {
    List<ShowtimeDetail> showtimes = showtimeService.getAllShowtimes();
    return new ResponseEntity<>(showtimes, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShowtimeDetail> getShowtimeById(@PathVariable Long id) {
    ShowtimeDetail showtime = showtimeService.getShowtimebyId(id);
    return new ResponseEntity<>(showtime, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Showtime> addShowtime(@RequestBody ShowtimeRequestDetail request) {
    Showtime showtime = showtimeService.addShowtime(request);
    return new ResponseEntity<>(showtime, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteShowtime(@PathVariable Long id) {
    showtimeService.deleteShowtime(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
