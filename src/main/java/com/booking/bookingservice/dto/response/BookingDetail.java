package com.booking.bookingservice.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
public class BookingDetail {

  private long id;
  private String personName;
  private Long showtimeId;
  private String movieTitle;
  private long auditoriumId;
  private int numberOfSeats;

}
