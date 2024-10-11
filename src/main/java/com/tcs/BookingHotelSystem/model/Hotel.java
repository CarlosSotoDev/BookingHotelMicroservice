package com.tcs.BookingHotelSystem.model;

// This class represents the 'Hotel' entity that will map to the 'hotel' table in the database.
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

// @Entity annotation indicates that this class is a JPA entity and will be mapped to a database table.
// The 'name' attribute specifies the name of the table, in this case 'hotel'.
@Entity(name = "hotel")

// @Data is a Lombok annotation that automatically generates getters, setters, toString(), equals(), and hashCode() methods.
@Data

// @NoArgsConstructor generates a no-argument constructor for the Hotel class.
@NoArgsConstructor

// @AllArgsConstructor generates a constructor with parameters for all fields of the class.
@AllArgsConstructor

// @Builder allows creating instances of the class using the builder pattern for more readable code.
@Builder
public class Hotel {

    // @Id indicates that the field 'id' is the primary key of the entity.
    // @GeneratedValue specifies that the ID should be generated automatically by the database.
    // The strategy GenerationType.IDENTITY ensures that the value is auto-incremented by the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // Unique identifier for each hotel record in the database.

    // 'hotelName' stores the name of the hotel.
    private String hotelName;

    // 'city' stores the city where the hotel is located.
    private String city;

    // 'checkinDate' stores the date when the customer checks into the hotel.
    // It is stored as a LocalDate, which represents a date without time (i.e., YYYY-MM-DD).
    private LocalDate checkinDate;

    // 'pricePerNight' stores the price per night to stay at the hotel.
    // It uses BigDecimal to handle currency values and avoid rounding issues with floating-point numbers.
    private BigDecimal pricePerNight;

}
