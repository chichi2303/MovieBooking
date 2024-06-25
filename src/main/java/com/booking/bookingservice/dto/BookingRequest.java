package com.booking.bookingservice.dto;

import lombok.*;

@Getter
@Setter
public class BookingRequest {

  private String customerName;
  private Long showtimeID;
  private int numberofTickets;
}
