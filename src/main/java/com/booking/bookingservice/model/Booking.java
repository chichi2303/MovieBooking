package com.booking.bookingservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String customerName;
  private int numberOfTickets;
  private LocalDateTime bookingTime;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "showtime_id")
  private Showtime showtime;
}
