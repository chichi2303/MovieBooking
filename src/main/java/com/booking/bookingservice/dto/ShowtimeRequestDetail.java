package com.booking.bookingservice.dto;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
public class ShowtimeRequestDetail {

  private long movieId;
  private String movieName;
  private long auditoriumId;
  private LocalDateTime time;
  private int availableSeats;
  private String signature;

}
