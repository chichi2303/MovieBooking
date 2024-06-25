package com.booking.bookingservice.repository;

import com.booking.bookingservice.model.Booking;
import com.booking.bookingservice.model.Showtime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

  List<Booking> findByShowtime(Showtime showtime);
}
