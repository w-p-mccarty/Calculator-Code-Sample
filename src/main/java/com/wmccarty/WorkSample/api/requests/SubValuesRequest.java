package com.wmccarty.WorkSample.api.requests;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Request to Subtract all the Values passed
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

public class SubValuesRequest {
    private List<Float> values;

    /**
     * Gets the values that will be subtracted
     *
     * @return List<Float> values being subtracted
     */
    @JsonGetter("Values")
    public List<Float> getValues() {
        if (values == null) {
            values = new ArrayList<>();
        }
        return values;
    }

    /**
     * Sets the values that will be subtracted
     *
     * @param values List<Float> values being subtracted
     */
    @JsonSetter("Values")
    public void setValues(List<Float> values) {
        this.values = values;
    }
}
