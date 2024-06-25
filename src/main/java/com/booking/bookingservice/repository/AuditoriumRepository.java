package com.booking.bookingservice.repository;

import com.booking.bookingservice.model.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {

}
