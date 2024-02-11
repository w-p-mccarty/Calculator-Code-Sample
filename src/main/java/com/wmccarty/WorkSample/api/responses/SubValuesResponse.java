package com.wmccarty.WorkSample.api.responses;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Response of Subtracted values
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class SubValuesResponse {
    private float result;

    /**
     * Gets the result value of subtracting values
     *
     * @return float subtracted values result
     */
    @JsonGetter("Result")
    public float getResult() {
        return result;
    }

    /**
     * Sets the result value of subtracting values
     *
     * @param result float subtracted values result
     */
    @JsonSetter("Result")
    public void setResult(float result) {
        this.result = result;
    }
}
