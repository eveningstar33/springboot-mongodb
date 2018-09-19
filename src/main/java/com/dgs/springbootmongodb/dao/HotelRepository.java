package com.dgs.springbootmongodb.dao;

import com.dgs.springbootmongodb.models.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String>, QueryDslPredicateExecutor<Hotel> {

    Hotel findById(String id);

    Hotel deleteById(String id);

    // findBy + PricePerNight (property name) + LessThan (filter)

    List<Hotel> findByPricePerNightLessThan(int maxPrice);

    @Query(value = "{address.city:?0}")
    List<Hotel> findByCity(String city);
}
