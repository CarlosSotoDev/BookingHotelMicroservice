package com.tcs.BookingHotelSystem.services;

import com.tcs.BookingHotelSystem.model.Hotel;
import com.tcs.BookingHotelSystem.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    //Methods
    //Get all Hotel
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    //CreateHotel
    public Hotel registerHotel(String hotelName, String city, LocalDate checkinDate, BigDecimal pricePerNight) {
        // Check for null or empty values in the fields
        if (hotelName == null || hotelName.trim().isEmpty()) {
            throw new IllegalArgumentException("Hotel name cannot be empty");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City field cannot be empty");
        }
        if (checkinDate == null) {
            throw new IllegalArgumentException("Check-in date field cannot be empty");
        }
        if (pricePerNight == null || pricePerNight.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price per night cannot be negative");
        }
        Hotel hotel = new Hotel();
        hotel.setHotelName(hotelName);
        hotel.setCity(city);
        hotel.setCheckinDate(checkinDate);
        hotel.setPricePerNight(pricePerNight);
        return hotelRepository.save(hotel);
    }


    //DeleteHotelById
    public String deleteHotelById(int id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            hotelRepository.deleteById(id);
            return "Hotel with id" + id + " was deleted";
        } else {
            return "Hotel with id" + id + " not found";
        }
    }

    //Update hotel by Id
    public String updateHotel(int id, String hotelName, String city, LocalDate checkinDate, BigDecimal pricePerNight) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            //Check for null or empty values in the fields
            if (hotelName == null || hotelName == "" || hotelName.trim().isEmpty()) {
                throw new IllegalArgumentException("Hotel name cannot be empty");
            }
            if (city == null || city == "" || city.trim().isEmpty()) {
                throw new IllegalArgumentException("City field cannot be empty");
            }
            if (checkinDate == null) {
                throw new IllegalArgumentException("checkinDate field cannot be empty");
            }
            if (pricePerNight == null || pricePerNight.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("PricePerNight field cannot be negative");
            }
            Hotel existingHotel = hotel.get();
            existingHotel.setHotelName(hotelName);
            existingHotel.setCity(city);
            existingHotel.setCheckinDate(checkinDate);
            existingHotel.setPricePerNight(pricePerNight);
            hotelRepository.save(existingHotel);
            return "Hotel with id" + id + " was updated";
        } else {
            return "Hotel with id" + id + " not found";
        }
    }

    //Finding Hotel By Id
    public Hotel getHotelById(int id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new RuntimeException("Hotel with id" + id + " not found");
        }
    }

    //Finding Hotel By HotelName
    public Hotel getHotelByHotelName(String hotelName) {
        Optional<Hotel> hotel = hotelRepository.findByHotelName(hotelName);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new RuntimeException("Hotel with id " + hotelName + " not found");
        }
    }

    //Finding Hotel By City
    public Hotel getHotelByCity(String city) {
        Optional<Hotel> hotel = hotelRepository.findByCity(city);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new RuntimeException("Hotel with id " + city + " not found");
        }
    }

    //Finding Hotel By Price
    public Hotel getHotelByPricePerNight(BigDecimal pricePerNight) {
        Optional<Hotel> hotel = hotelRepository.findByPricePerNight(pricePerNight);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new RuntimeException("Hotel with id " + pricePerNight + " not found");
        }
    }

}
