package com.booking.bookingservice.mapper;

import com.booking.bookingservice.dto.response.ShowtimeDetail;
import com.booking.bookingservice.model.Showtime;

public class ShowtimeMapper {

  public static ShowtimeDetail mapToShowtimeDetail(Showtime showtime) {
    return ShowtimeDetail.builder()
        .id(showtime.getId())
        .startTime(showtime.getStartTime())
        .availableSeats(showtime.getAvailableSeats())
        .movieId(showtime.getMovie().getId())
        .auditoriumId(showtime.getAuditorium().getId())
        .build();
  }
}
