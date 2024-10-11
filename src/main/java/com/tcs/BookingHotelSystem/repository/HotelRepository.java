package com.tcs.BookingHotelSystem.repository;

import com.tcs.BookingHotelSystem.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    //Must to be the same like the atributs from model
    Optional<Hotel> findByHotelName(String hotelName);

    Optional<Hotel> findByCity(String city);

    Optional<Hotel> findByPricePerNight(BigDecimal pricePerNight);

    Optional<Hotel> findByCheckinDate(LocalDate checkinDate);
}
