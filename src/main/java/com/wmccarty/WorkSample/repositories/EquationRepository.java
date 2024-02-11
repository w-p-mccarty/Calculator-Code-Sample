package com.wmccarty.WorkSample.repositories;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Repository interface for Equation Entity
 **/

import com.wmccarty.WorkSample.entity.Equation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquationRepository extends MongoRepository<Equation, String> {
}
