package com.booking.bookingservice.dto;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
public class ShowtimeRequest {

  private long movieID;
  private long auditoriumId;
  private LocalDateTime time;
  private int avaliableSeats;

}
