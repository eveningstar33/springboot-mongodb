package com.dgs.springbootmongodb.controller;

import com.dgs.springbootmongodb.dao.HotelRepository;
import com.dgs.springbootmongodb.models.Hotel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/all")
    public List<Hotel> getAllHotels() {

        List<Hotel> hotels = hotelRepository.findAll();

        return hotels;
    }

    @GetMapping("/{id}")
    public Optional<Hotel> getOneHotel(@PathVariable String id) {

        return hotelRepository.findById(id);
    }

    @PostMapping
    public Hotel create(@RequestBody Hotel hotel) {

        return hotelRepository.save(hotel);
    }

    @PutMapping
    public Hotel update(@RequestBody Hotel hotel) {

        return hotelRepository.save(hotel);
    }

    @DeleteMapping("/delete/{id}")
    public List<Hotel> delete(@PathVariable String id) {

        hotelRepository.deleteById(id);

        return hotelRepository.findAll();
    }

    @GetMapping("/price/{maxPrice}")
    public List<Hotel> getByPricePerNight(@PathVariable int maxPrice) {

        List<Hotel> hotels = hotelRepository.findByPricePerNightLessThan(maxPrice);

        return hotels;
    }

    @GetMapping("address/{city}")
    public List<Hotel> getByCity(@PathVariable String city) {

        List<Hotel> hotels = hotelRepository.findByCity(city);

        return hotels;
    }
}
