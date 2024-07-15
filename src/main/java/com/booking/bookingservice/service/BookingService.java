package com.booking.bookingservice.service;

import com.booking.bookingservice.dto.BookingRequestDetail;
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

  public BookingDetail bookShowtime(BookingRequestDetail requestDetail) {
    // TODO fix performance
    // check showtime exist. pull all showTime object from database
    Showtime showtime = showtimeRepository.findById(requestDetail.getShowtimeId())
        .orElseThrow(() -> new RuntimeException("Showtime not found"));
    // -> 2 query
    // check enough seat
    if (showtime.getAvailableSeats() < requestDetail.getNumberOfTickets()) {
      throw new RuntimeException("Not enough seats available");
    }
    // update seat after booking
    showtime.setAvailableSeats(showtime.getAvailableSeats() - requestDetail.getNumberOfTickets());
    // Save the updated showtime entity to the database
    // save all showTime object to database
    showtimeRepository.save(showtime);

    Booking booking = Booking.builder()
        .customerName(requestDetail.getCustomerName())
        .showtime(showtime)
        .numberOfTickets(requestDetail.getNumberOfTickets())
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
