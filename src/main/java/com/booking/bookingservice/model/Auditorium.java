package com.booking.bookingservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Auditorium {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String roomNumber;
  private Integer capacity;

  @OneToOne(mappedBy = "auditorium", cascade = CascadeType.ALL, orphanRemoval = true)
  private Showtime showtime;

}

