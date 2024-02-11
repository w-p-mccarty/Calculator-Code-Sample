package com.wmccarty.WorkSample.repositories;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Repository interface for Calculation Entity
 **/

import com.wmccarty.WorkSample.entity.Calculation;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CalculationRepository extends MongoRepository<Calculation, String> {
}
