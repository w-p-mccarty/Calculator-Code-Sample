package com.wmccarty.WorkSample.api.responses;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary :Response to Clear a Equation
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ClearEquationResponse {
    private boolean success;

    /**
     * Gets if the request was successful
     *
     * @return boolean if successfully cleared
     */
    @JsonGetter("Success")
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets if request was successful
     *
     * @param success boolean if successfully cleared
     */
    @JsonSetter("Success")
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
