package com.booking.moviebooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Movie {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  Long id;
private String title;
private String genre;
private LocalDateTime showTime;


}
