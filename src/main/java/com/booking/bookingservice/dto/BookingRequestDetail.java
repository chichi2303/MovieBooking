package com.booking.bookingservice.dto;

import lombok.Data;

@Data
public class BookingRequestDetail {

  private String customerName;
  private Long showtimeId;
  private int numberOfTickets;
  private String signature;
}
