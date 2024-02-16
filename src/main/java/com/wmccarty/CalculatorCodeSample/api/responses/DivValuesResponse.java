package com.wmccarty.CalculatorCodeSample.api.responses;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Response of Divided Values request
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class DivValuesResponse {
    private float result;

    /**
     * Gets the result value of dividing values
     *
     * @return float divided values result
     */
    @JsonGetter("Result")
    public float getResult() {
        return result;
    }

    /**
     * Sets the result value of dividing values
     *
     * @param result float dividing values result
     */
    @JsonSetter("Result")
    public void setResult(float result) {
        this.result = result;
    }
}
