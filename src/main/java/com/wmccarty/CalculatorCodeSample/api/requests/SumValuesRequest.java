package com.wmccarty.CalculatorCodeSample.api.requests;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Request to Sum up all values
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

public class SumValuesRequest {
    private List<Float> values;

    /**
     * Gets the values that will be summed
     *
     * @return List<Float> values being summed
     */
    @JsonGetter("Values")
    public List<Float> getValues() {
        if (values == null) {
            values = new ArrayList<>();
        }
        return values;
    }

    /**
     * Sets the values that will be summed
     *
     * @param values List<Float> values being summed
     */
    @JsonSetter("Values")
    public void setValues(List<Float> values) {
        this.values = values;
    }
}
