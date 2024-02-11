package com.wmccarty.WorkSample.api.requests;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Request to Print the Equation
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class PrintEquationRequest {
    private String id;

    /**
     * Gets the Id of the Equation we are going to print
     *
     * @return String Id of the Equation we are printing
     */
    @JsonGetter("Id")
    public String getId() {
        return id;
    }

    /**
     * Sets the Id of the Equation we are printing
     *
     * @param id String id we are wanting to print
     */
    @JsonSetter("Id")
    public void setId(String id) {
        this.id = id;
    }
}
