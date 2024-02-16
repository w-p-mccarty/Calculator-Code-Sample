package com.wmccarty.CalculatorCodeSample.api.responses;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Response of Multiplying values
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class MultValuesResponse {
    private float result;

    /**
     * Gets the result value of multiplying values
     *
     * @return float multiplied values result
     */
    @JsonGetter("Result")
    public float getResult() {
        return result;
    }

    /**
     * Sets the result value of multiplying values
     *
     * @param result float multiplied values result
     */
    @JsonSetter("Result")
    public void setResult(float result) {
        this.result = result;
    }
}
