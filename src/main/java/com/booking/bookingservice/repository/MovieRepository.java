package com.booking.bookingservice.repository;

import com.booking.bookingservice.model.Movie;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long> {

  Optional<Movie> findById(long id);

  @Query(value = "SELECT COUNT(*) > 0 FROM movie m WHERE m.id = :movieId", nativeQuery = true)
  boolean existsByMovieId(@Param("movieId") Long movieId);
}
