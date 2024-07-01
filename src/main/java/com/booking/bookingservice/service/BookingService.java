package com.booking.bookingservice.service;

import com.booking.bookingservice.model.Booking;
import com.booking.bookingservice.model.Showtime;
import com.booking.bookingservice.repository.BookingRepository;
import com.booking.bookingservice.repository.ShowtimeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

  private final BookingRepository bookingRepository;
  private final ShowtimeRepository showtimeRepository;

  public BookingService(BookingRepository bookingRepository,
      ShowtimeRepository showtimeRepository) {
    this.bookingRepository = bookingRepository;
    this.showtimeRepository = showtimeRepository;
  }


  public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
  }

  public Optional<Booking> getBookingById(Long id) {
    return bookingRepository.findById(id);
  }

  public Booking bookShowtime(String personName, Long showtimeId, int numberOfSeats) {
    Showtime showtime = showtimeRepository.findById(showtimeId)
        .orElseThrow(() -> new RuntimeException("Showtime not found"));
    if (showtime.getAvailableSeats() < numberOfSeats) {
      throw new RuntimeException("Not enough seats available");
    }
    // TODO: do we need to update showtime in database after setAvailableSeats?
    showtime.setAvailableSeats(showtime.getAvailableSeats() - numberOfSeats);
    Booking booking = Booking.builder()
        .customerName(personName)
        .showtime(showtime)
        .numberOfTickets(numberOfSeats)
        .build();
    return bookingRepository.save(booking);
  }

  public void deleteBooking(Long id) {
    Booking booking = bookingRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Booking not found"));

    Showtime showtime = booking.getShowtime();
    showtime.setAvailableSeats(showtime.getAvailableSeats() + booking.getNumberOfTickets());
    bookingRepository.delete(booking);
  }
}
