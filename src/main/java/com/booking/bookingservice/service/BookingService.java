package com.booking.bookingservice.service;

import com.booking.bookingservice.dto.response.BookingDetail;
import com.booking.bookingservice.mapper.BookingMapper;
import com.booking.bookingservice.model.Booking;
import com.booking.bookingservice.model.Showtime;
import com.booking.bookingservice.repository.BookingRepository;
import com.booking.bookingservice.repository.ShowtimeRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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


  public List<BookingDetail> getAllBookings() {
    List<Booking> bookings = bookingRepository.findAll();
    return bookings.stream()
        .map(BookingMapper::mapToBookingDetail)
        .collect(Collectors.toList());
  }

  public Optional<BookingDetail> getBookingById(Long id) {
    return bookingRepository.findById(id)
        .map(BookingMapper::mapToBookingDetail);
  }

  public BookingDetail bookShowtime(String personName, Long showtimeId, int numberOfSeats) {
    Showtime showtime = showtimeRepository.findById(showtimeId)
        .orElseThrow(() -> new RuntimeException("Showtime not found"));
    if (showtime.getAvailableSeats() < numberOfSeats) {
      throw new RuntimeException("Not enough seats available");
    }
    showtime.setAvailableSeats(showtime.getAvailableSeats() - numberOfSeats);
    // Save the updated showtime entity to the database
    showtimeRepository.save(showtime);

    Booking booking = Booking.builder()
        .customerName(personName)
        .showtime(showtime)
        .numberOfTickets(numberOfSeats)
        .build();
    Booking savedBooking = bookingRepository.save(booking);
    return BookingMapper.mapToBookingDetail(savedBooking);
  }

  public void deleteBooking(Long id) {
    Booking booking = bookingRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Booking not found"));

    Showtime showtime = booking.getShowtime();
    showtime.setAvailableSeats(showtime.getAvailableSeats() + booking.getNumberOfTickets());
    // Save the updated showtime entity to the database
    showtimeRepository.save(showtime);

    bookingRepository.delete(booking);
  }
}
