package com.booking.bookingservice.mapper;

import com.booking.bookingservice.dto.response.BookingDetail;
import com.booking.bookingservice.model.Booking;

public class BookingMapper {

  public static BookingDetail mapToBookingDetail(Booking booking) {
    return BookingDetail.builder()
        .id(booking.getId())
        .personName(booking.getCustomerName())
        .showtimeId(booking.getShowtime().getId())
        .movieTitle(booking.getShowtime().getMovie().getTitle())
        .auditoriumId(booking.getShowtime().getAuditorium().getId())
        .numberOfSeats(booking.getNumberOfTickets())
        .build();
  }
}
