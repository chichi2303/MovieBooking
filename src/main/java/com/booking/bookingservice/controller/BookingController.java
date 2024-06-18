package com.booking.bookingservice.controller;

import com.booking.bookingservice.model.Booking;
import com.booking.bookingservice.service.BookingService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bookings")
public class BookingController {

  private final BookingService bookingService;

  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @GetMapping
  public List<Booking> getAllBookings() {
    return bookingService.getAllBookings();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
    return bookingService.getBookingById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Booking createBooking(@RequestBody Booking booking) {
    return bookingService.saveBooking(booking);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
    bookingService.deleteBooking(id);
    return ResponseEntity.noContent().build();
  }
}
