package com.booking.bookingservice.controller;

import com.booking.bookingservice.dto.BookingRequestDetail;
import com.booking.bookingservice.dto.response.BookingDetail;
import com.booking.bookingservice.service.BookingService;
import java.util.List;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<List<BookingDetail>> getAllBookings() {
    List<BookingDetail> allBookings = bookingService.getAllBookings();
    return new ResponseEntity<>(allBookings, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookingDetail> getBookingById(@PathVariable Long id) {
    return bookingService.getBookingById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<BookingDetail> bookShowtime(@RequestBody BookingRequestDetail request) {
    // TODO: map rest request - DONE
    BookingDetail bookingDetail = bookingService.bookShowtime(request);
    return new ResponseEntity<>(bookingDetail, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
    bookingService.deleteBooking(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
