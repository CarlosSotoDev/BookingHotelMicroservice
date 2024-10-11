package com.tcs.BookingHotelSystem.controller;

import com.tcs.BookingHotelSystem.model.Hotel;
import com.tcs.BookingHotelSystem.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    //Get All Hotels
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    // Endpoint to create a new hotel
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        try {
            // Call the service to register the hotel
            Hotel createdHotel = hotelService.registerHotel(
                    hotel.getHotelName(),
                    hotel.getCity(),
                    hotel.getCheckinDate(),
                    hotel.getPricePerNight()
            );
            return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Return a bad request response if there are validation issues
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Return error response in case of failure
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to delete a hotel by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotelById(@PathVariable int id) {
        try {
            // Call the service to delete the hotel
            String result = hotelService.deleteHotelById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            // Return error response in case of failure
            return new ResponseEntity<>("Error deleting hotel.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to update an existing hotel
    @PutMapping("/{id}")
    public ResponseEntity<String> updateHotel(
            @PathVariable int id,
            @RequestBody Hotel hotelRequest) {
        try {
            // Call the service to update the hotel
            String result = hotelService.updateHotel(
                    id, hotelRequest.getHotelName(),
                    hotelRequest.getCity(),
                    hotelRequest.getCheckinDate(),
                    hotelRequest.getPricePerNight()
            );
            if (result.contains("successfully updated")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Return an error response in case of failure
            return new ResponseEntity<>("Error updating hotel.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to retrieve a hotel by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable int id) {
        try {
            // Call the service to retrieve the hotel
            Hotel hotel = hotelService.getHotelById(id);
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } catch (Exception e) {
            // Return error response if hotel not found
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to retrieve a hotel by its hotelName
    @GetMapping("/hotelName/{hotelName}")
    public ResponseEntity<Hotel> getHotelByHotelName(@PathVariable String hotelName) {
        try {
            // Call the service to retrieve the hotel
            Hotel hotel = hotelService.getHotelByHotelName(hotelName);
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } catch (Exception e) {
            // Return error response if hotel not found
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to retrieve a hotel by its hotelName
    @GetMapping("/city/{city}")
    public ResponseEntity<Hotel> getHotelByCity(@PathVariable String city) {
        try {
            // Call the service to retrieve the hotel
            Hotel hotel = hotelService.getHotelByCity(city);
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } catch (Exception e) {
            // Return error response if hotel not found
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to retrieve a hotel by its hotelName
    @GetMapping("/ppn/{pricePerNight}")
    public ResponseEntity<Hotel> getHotelByPricePerNight(@PathVariable BigDecimal pricePerNight) {
        try {
            // Call the service to retrieve the hotel
            Hotel hotel = hotelService.getHotelByPricePerNight(pricePerNight);
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } catch (Exception e) {
            // Return error response if hotel not found
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
