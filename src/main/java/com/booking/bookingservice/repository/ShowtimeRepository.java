package com.booking.bookingservice.repository;

import com.booking.bookingservice.model.Movie;
import com.booking.bookingservice.model.Showtime;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

  // TODO: find by id instead of find by object
  List<Showtime> findByMovie(Long id);

  @Query(value = "SELECT COUNT(*)>0 FROM showtime s WHERE s.movie_id = :movieId AND s"
      + ".auditorium_id = :auditoriumId AND s.start_time =:time", nativeQuery = true)
  boolean existsByMovieIdAndAuditoriumIdAndTime(@Param("movieId") Long movieId, @Param(
      "auditoriumID") Long auditoriumID, @Param("time") LocalDateTime time);

  @Query(value = "SELECT COUNT(*) > 0 FROM showtime s WHERE s.movie_id = :movieId", nativeQuery = true)
  boolean existsByMovieId(@Param("movieId") Long movieId);

}
