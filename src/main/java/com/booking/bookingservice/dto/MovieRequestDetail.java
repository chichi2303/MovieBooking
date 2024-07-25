package com.booking.bookingservice.dto;

import com.booking.bookingservice.dto.response.ShowtimeDetail;
import java.util.List;
import lombok.Data;

@Data
public class MovieRequestDetail {

  private Long id;
  private String title;
  private String genre;
  private String director;
  private List<ShowtimeDetail> showtimes;
  private String signature;

}
