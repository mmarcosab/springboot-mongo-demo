package com.example.demo.adapters.database.repository;

import com.example.demo.adapters.database.document.RiceProductionData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataRiceProductionRepository extends MongoRepository<RiceProductionData, String> {
    Page<RiceProductionData> findByArea(final String area, final Pageable pageable);
    @Query("{ 'value' : { $gte: ?0 } }")
    Page<RiceProductionData> listByValueEqualOrHigher(final String value, final Pageable pageable);
    @Query("{ 'value' : {$lte: ?0 } }")
    Page<RiceProductionData> listByValueEqualOrLess(final String value, final Pageable pageable);
    Page<RiceProductionData> findByValue(final String value, final Pageable pageable);
    @Query("{$or:[{'area': ?0}, {'area': ?1 }]}")
    Page<RiceProductionData> findByAreas(final String areaOne, final String areaTwo, final Pageable pageable);
    @Query("{'value': {$in: [ ?0,  ?1 ]}}")
    Page<RiceProductionData> findByValues(final String valueBegin, final String valueEnd, final Pageable pageable);
    @Query("{'year': {$in: [ ?0,  ?1 ]}}")
    Page<RiceProductionData> findByPeriod(final String yearBegin, final String yearEnd, final Pageable pageable);
}
