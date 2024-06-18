package com.booking.bookingservice.repository;

import com.booking.bookingservice.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

}
