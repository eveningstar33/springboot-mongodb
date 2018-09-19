package com.dgs.springbootmongodb.controller;

import com.dgs.springbootmongodb.models.Hotel;
import com.dgs.springbootmongodb.dao.HotelRepository;
import com.dgs.springbootmongodb.models.QHotel;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Hotel getOneHotel(@PathVariable String id) {

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

    @GetMapping("/country/{country}")
    public List<Hotel> getByCountry(@PathVariable String country) {

        // create a query class (QHotel)
        QHotel qhotel = new QHotel("hotel");

        // using the query class we can create the filters
        // I want to filter by address, then country and then I want to apply an equality operator
        // and I'll pass in the country that is bounded to my path variable
        BooleanExpression filterByCountry = qhotel.address.country.eq(country);

        // we can then pass the filters to the findAll() method
        List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll(filterByCountry);

        return hotels;
    }

    @GetMapping("/recommended")
    public List<Hotel> getRecommended() {

        final int maxPrice = 100;
        final int minRating = 7;

        // create a query class (QHotel)
        QHotel qHotel = new QHotel("hotel");

        // one filter for max price and one filter for minimmum rating
        //lt = less than, gt = greater than

        // using the query class we can create the filters
        BooleanExpression filterByPrice = qHotel.pricePerNight.lt(maxPrice);
        BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(minRating);

        // we can then pass the filters to the findAll() method
        List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll(filterByPrice.and(filterByRating));

        return hotels;
    }
}
