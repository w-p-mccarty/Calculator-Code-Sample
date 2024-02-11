package com.wmccarty.WorkSample.api.requests;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Request to Evaluate the Equation
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class EvalEquationRequest {
    private String id;

    /**
     * Gets the Id of the Equation we are going to evaluate
     *
     * @return String Id of the Equation we are going to evaluate
     */
    @JsonGetter("Id")
    public String getId() {
        return id;
    }

    /**
     * Sets the Id of the Equation we are evaluating
     *
     * @param id String id we are wanting to evaluate
     */
    @JsonSetter("Id")
    public void setId(String id) {
        this.id = id;
    }
}
