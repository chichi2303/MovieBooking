package com.booking.bookingservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrpcBookingRequest {

  private String customerName;
  private Long showtimeID;
  private String numberofTickets;
  private String signature;
}
