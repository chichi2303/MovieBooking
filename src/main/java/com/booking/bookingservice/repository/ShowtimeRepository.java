package com.booking.bookingservice.repository;

import com.booking.bookingservice.model.Showtime;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

  // TODO: find by id instead of find by object -DONE
  List<Showtime> findByMovieId(Long id);

  @Query(value = "SELECT COUNT(*)>0 FROM showtime s WHERE s.movie_id = :movieId AND s"
      + ".auditorium_id = :auditoriumId AND s.start_time =:time", nativeQuery = true)
  boolean existsByMovieIdAndAuditoriumIdAndTime(@Param("movieId") long movieId, @Param(
      "auditoriumId") long auditoriumId, @Param("time") LocalDateTime time);

  @Query(value = "SELECT COUNT(*) > 0 FROM showtime s WHERE s.movie_id = :movieId", nativeQuery = true)
  boolean existsByMovieId(@Param("movieId") Long movieId);

  @Query("SELECT s FROM Showtime s WHERE s.id = :showtimeId AND s.availableSeats >= :numberOfTickets")
  Optional<Showtime> findByIdAndAvailableSeats(@Param("showtimeId") Long showtimeId,
      @Param("numberOfTickets") int numberOfTickets);

}
