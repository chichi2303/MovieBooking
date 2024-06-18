package com.booking.bookingservice.controller;

import com.booking.bookingservice.model.Showtime;
import com.booking.bookingservice.service.ShowtimeService;
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
  public List<Showtime> getAllShowtimes() {
    return showtimeService.getAllShowtimes();
  }

  @GetMapping("/{id}")
  public Showtime getShowtimeById(@PathVariable Long id) {
    return showtimeService.getShowtimebyId(id);
  }

  @PostMapping
  public Showtime createShowtime(@RequestBody Showtime showtime) {
    return showtimeService.saveShowtime(showtime);
  }

  @DeleteMapping("/{id}")
  public void deleteShowtime(@PathVariable Long id) {
    showtimeService.deleteShowtime(id);
  }
}
