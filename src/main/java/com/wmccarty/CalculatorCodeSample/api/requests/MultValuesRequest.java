package com.wmccarty.CalculatorCodeSample.api.requests;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Request to Multiply all values passed in
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

public class MultValuesRequest {
    private List<Float> values;

    /**
     * Gets the values that will be multiplied
     *
     * @return List<Float> values being multiplied
     */
    @JsonGetter("Values")
    public List<Float> getValues() {
        if (values == null) {
            values = new ArrayList<>();
        }
        return values;
    }

    /**
     * Sets the values that will be multiplied
     *
     * @param values List<Float> values being multiplied
     */
    @JsonSetter("Values")
    public void setValues(List<Float> values) {
        this.values = values;
    }
}
