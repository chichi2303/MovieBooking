package com.booking.bookingservice.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String customerName;
  private int numberOfTickets;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "showtime_id")
  private Showtime showtime;
}
