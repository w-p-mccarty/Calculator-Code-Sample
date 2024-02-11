package com.wmccarty.WorkSample.api.responses;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Response of Printing Equation
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class PrintEquationResponse {
    private String equation;

    /**
     * Gets Equation in readable format
     *
     * @return String equation in form 1 + 2 - 3 * 4 / 5
     */
    @JsonGetter("Equation")
    public String getEquation() {
        return equation;
    }

    /**
     * Sets Equation for readablity
     *
     * @param equation String equation
     */
    @JsonSetter("Equation")
    public void setEquation(String equation) {
        this.equation = equation;
    }
}
