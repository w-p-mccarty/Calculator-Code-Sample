package com.wmccarty.WorkSample.api.responses;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Response of Evaluated Equation
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class EvalEquationResponse {
    private float result;

    /**
     * Gets the result value of the equation
     *
     * @return float equation result
     */
    @JsonGetter("Result")
    public float getResult() {
        return result;
    }

    /**
     * Sets the result value of the equation
     *
     * @param result float equation result
     */
    @JsonSetter("Result")
    public void setResult(float result) {
        this.result = result;
    }
}
