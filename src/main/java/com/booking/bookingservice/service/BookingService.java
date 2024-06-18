package com.booking.bookingservice.service;

import com.booking.bookingservice.model.Booking;
import com.booking.bookingservice.repository.BookingRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

  private final BookingRepository bookingRepository;

  public BookingService(BookingRepository bookingRepository) {
    this.bookingRepository = bookingRepository;
  }


  public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
  }

  public Optional<Booking> getBookingById(Long id) {
    return bookingRepository.findById(id);
  }

  public Booking saveBooking(Booking booking) {
    return bookingRepository.save(booking);
  }

  public void deleteBooking(Long id) {
    bookingRepository.deleteById(id);
  }
}
