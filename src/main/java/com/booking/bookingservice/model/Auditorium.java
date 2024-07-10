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
  private long id;
  private String roomNumber;
  private int capacity;

  @Column(name = "showtime_id", insertable = false, updatable = false)
  private long showtimeId;

  @OneToOne(mappedBy = "auditorium", cascade = CascadeType.ALL, orphanRemoval = true)
  private Showtime showtime;

}
