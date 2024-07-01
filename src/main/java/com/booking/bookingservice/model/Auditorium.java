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

  // TODO: add FK with updatable/insertable
  private long showtimeId;

  @OneToOne(mappedBy = "auditorium", cascade = CascadeType.ALL, orphanRemoval = true)
  private Showtime showtime;

}
