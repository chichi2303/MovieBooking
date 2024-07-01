package com.booking.bookingservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Showtime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private LocalDateTime startTime;
  private int availableSeats;

  @Column(insertable = false, updatable = false)
  private long movieId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "movie_id")
  private Movie movie;

  @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Booking> bookings;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "auditorium_id")
  private Auditorium auditorium;

}
