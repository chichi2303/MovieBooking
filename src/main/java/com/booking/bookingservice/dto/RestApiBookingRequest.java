package com.booking.bookingservice.dto;

import lombok.*;

@Getter
@Setter
public class RestApiBookingRequest {

  private String customerName;
  private Long showtimeID;
  private int numberofTickets;
  private String signature;
}
