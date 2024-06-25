package com.booking.bookingservice.model;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String genre;
  private String director;

  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Showtime> showtimes;

}
