package com.wmccarty.CalculatorCodeSample.api.requests;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Request to Delete the Equation from the Table
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ClearEquationRequest {
    private String id;

    /**
     * Gets the Id of the Equation we are clearing
     *
     * @return String Id of the Equation we are clearing
     */
    @JsonGetter("Id")
    public String getId() {
        return id;
    }

    /**
     * Sets the Id of the Equation we are clearing
     *
     * @param id String id we are wanting to clear
     */
    @JsonSetter("Id")
    public void setId(String id) {
        this.id = id;
    }
}
