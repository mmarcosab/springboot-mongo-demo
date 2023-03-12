package com.example.demo.adapters.database.repository;

import com.example.demo.adapters.database.document.RiceProductionData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataRiceProductionRepository extends MongoRepository<RiceProductionData, String> {
    List<RiceProductionData> findByArea(final String countryName);
}
