package com.wmccarty.CalculatorCodeSample.service;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Interface for Calculation Service Methods
 **/

import java.util.List;

public interface CalculationServiceMethods {
    /**
     * Sums the passed in Float values
     *
     * @param values Float values needing to be summed
     * @return float sum value of all elements
     */
    float sumValues(List<Float> values);

    /**
     * Subtracts the passed in Float values
     *
     * @param values Float values needing to be subtracted
     * @return float result value of all elements
     */
    float subValues(List<Float> values);

    /**
     * Divides the passed in Float values
     *
     * @param values Float values needing to be divided
     * @return float result value of all elements
     */
    float divValues(List<Float> values);

    /**
     * Multiplies the passed in Float values
     *
     * @param values Float values needing to be Multiplied
     * @return float result value of all elements
     */
    float multValues(List<Float> values);

}
