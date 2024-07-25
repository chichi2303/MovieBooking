package com.booking.bookingservice.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowtimeDetail {

  private long id;
  private LocalDateTime startTime;
  private int availableSeats;
  private Long movieId;
  private Long auditoriumId;
}
