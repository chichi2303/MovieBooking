package com.booking.bookingservice.dto;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
public class ShowtimeRequest {

  // TODO: convert from object to primitive type
  private Long movieID;
  private Long auditoriumId;
  private LocalDateTime time;
  private int avaliableSeats;

}
