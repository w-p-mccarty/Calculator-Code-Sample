package com.wmccarty.WorkSample.api.responses;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Response of Summed values
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class SumValuesResponse {
    private float result;

    /**
     * Gets the result value of summing values
     *
     * @return float summed values result
     */
    @JsonGetter("Result")
    public float getResult() {
        return result;
    }

    /**
     * Sets the result value of summing values
     *
     * @param result float summed values result
     */
    @JsonSetter("Result")
    public void setResult(float result) {
        this.result = result;
    }
}
