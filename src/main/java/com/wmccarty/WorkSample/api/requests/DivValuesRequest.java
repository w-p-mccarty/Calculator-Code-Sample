package com.wmccarty.WorkSample.api.requests;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Request to Divide all the values passed
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

public class DivValuesRequest {
    private List<Float> values;

    /**
     * Gets the values that will be divided
     *
     * @return List<Float> values being divided
     */
    @JsonGetter("Values")
    public List<Float> getValues() {
        if (values == null) {
            values = new ArrayList<>();
        }
        return values;
    }

    /**
     * Sets the values that will be divided
     *
     * @param values List<Float> values being divided
     */
    @JsonSetter("Values")
    public void setValues(List<Float> values) {
        this.values = values;
    }
}
