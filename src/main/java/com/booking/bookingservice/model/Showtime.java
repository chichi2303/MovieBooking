package com.booking.bookingservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Showtime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private LocalDateTime startTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "movie_id")
  private Movie movie;

  @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Booking> bookings;

}
