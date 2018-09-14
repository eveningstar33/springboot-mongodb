package com.dgs.springbootmongodb.dao;

import com.dgs.springbootmongodb.models.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepository extends MongoRepository<Hotel, String> {

}
