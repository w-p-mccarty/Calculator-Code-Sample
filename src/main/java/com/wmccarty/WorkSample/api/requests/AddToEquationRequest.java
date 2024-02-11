package com.wmccarty.WorkSample.api.requests;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Request to Add Terms to a new or existing equation
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class AddToEquationRequest {
    private String id;
    private String term;

    /**
     * Gets the Id of the Equation we are adding to
     *
     * @return String Id of the Equation we are adding to
     */
    @JsonGetter("Id")
    public String getId() {
        return id;
    }

    /**
     * Sets the Id of the Equation we are adding to
     *
     * @param id String id we are wanting to add to
     */
    @JsonSetter("Id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the Term we are going to add
     *
     * @return String term that we are going to add
     */
    @JsonGetter("Term")
    public String getTerm() {
        return term;
    }

    /**
     * Sets the Term we are going to add
     *
     * @param term String term that we are going to add
     */
    @JsonSetter("Term")
    public void setTerm(String term) {
        this.term = term;
    }
}
