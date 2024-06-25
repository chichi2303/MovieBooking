package com.booking.bookingservice.repository;

import com.booking.bookingservice.model.Movie;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

  Optional<Movie> findByTitle(String title);
}
