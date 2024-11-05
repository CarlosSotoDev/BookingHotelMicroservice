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

    // Obtener todos los hoteles
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    // Crear un nuevo hotel
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        try {
            Hotel createdHotel = hotelService.registerHotel(
                    hotel.getHotelName(),
                    hotel.getCity(),
                    hotel.getCheckinDate(),
                    hotel.getPricePerNight()
            );
            return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un hotel por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotelById(@PathVariable int id) {
        try {
            String result = hotelService.deleteHotelById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting hotel.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un hotel existente
    @PutMapping("/{id}")
    public ResponseEntity<String> updateHotel(
            @PathVariable int id,
            @RequestBody Hotel hotelRequest) {
        try {
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
            return new ResponseEntity<>("Error updating hotel.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener un hotel por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable int id) {
        try {
            Hotel hotel = hotelService.getHotelById(id);
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Obtener un hotel por su nombre
    @GetMapping("/hotelName/{hotelName}")
    public ResponseEntity<Hotel> getHotelByHotelName(@PathVariable String hotelName) {
        try {
            Hotel hotel = hotelService.getHotelByHotelName(hotelName);
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Obtener hoteles por ciudad
    @GetMapping("/search")
    public ResponseEntity<List<Hotel>> getHotelsByCity(@RequestParam("city") String city) {
        List<Hotel> hotels = hotelService.findHotelsByCity(city);
        if (hotels.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(hotels);
        }
    }

    // Obtener un hotel por su precio por noche
    @GetMapping("/ppn/{pricePerNight}")
    public ResponseEntity<Hotel> getHotelByPricePerNight(@PathVariable BigDecimal pricePerNight) {
        try {
            Hotel hotel = hotelService.getHotelByPricePerNight(pricePerNight);
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
