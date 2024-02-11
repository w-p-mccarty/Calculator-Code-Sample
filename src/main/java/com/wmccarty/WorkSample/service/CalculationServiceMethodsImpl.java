package com.wmccarty.WorkSample.service;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Implementation of CalculationServiceMethods that Processes Calculation methods for a set of data
 **/

import com.wmccarty.WorkSample.entity.Calculation;
import com.wmccarty.WorkSample.repositories.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CalculationServiceMethodsImpl implements CalculationServiceMethods {

    @Autowired
    private CalculationRepository calculationRepository;

    /**
     * Sums the passed in Float values
     *
     * @param values Float values needing to be summed
     * @return float sum value of all elements
     */
    public float sumValues(List<Float> values) {
        String equation = "";
        float result = 0f;
        //Pass in values
        for (Float val : values) {
            if (val != null) {
                //If Equation has text then it isn't the first element
                if (StringUtils.hasText(equation)) {
                    equation += " + ";
                }
                equation += String.valueOf(val.floatValue());
                result += val.floatValue();
            }
        }
        saveEquation(equation, result);
        return result;
    }

    /**
     * Subtracts the passed in Float values
     *
     * @param values Float values needing to be subtracted
     * @return float result value of all elements
     */
    public float subValues(List<Float> values) {
        String equation = "";
        float result = 0f;
        //Pass in values
        for (Float val : values) {
            if (val != null) {
                if (!StringUtils.hasText(equation)) {
                    //This is the first element
                    result = val;
                } else {
                    equation += " - ";
                    result -= val.floatValue();
                }
                equation += String.valueOf(val.floatValue());

            }
        }
        saveEquation(equation, result);
        return result;
    }

    /**
     * Divides the passed in Float values
     *
     * @param values Float values needing to be divided
     * @return float result value of all elements
     */
    public float divValues(List<Float> values) {
        String equation = "";
        float result = 0f;
        boolean isUndefined = false;
        //Pass in values
        for (Float val : values) {
            if (val != null) {
                if (!StringUtils.hasText(equation)) {
                    //This is first element
                    result = val;
                } else {
                    //We can't divide by 0, so the only valid 0 is the first item
                    if (!isUndefined && val.floatValue() != 0) {
                        result /= val.floatValue();
                    } else {
                        //Flag that this value is undefined - but continue to display equation
                        isUndefined = true;
                        result = 0f;
                    }
                    equation += " / ";
                }
                equation += String.valueOf(val.floatValue());

            }
        }
        saveEquation(equation, result);
        return result;
    }

    /**
     * Multiplies the passed in Float values
     *
     * @param values Float values needing to be Multiplied
     * @return float result value of all elements
     */
    public float multValues(List<Float> values) {
        String equation = "";
        float result = 0f;
        //Pass in values
        for (Float val : values) {
            if (val != null) {
                if (!StringUtils.hasText(equation)) {
                    //This is the first element
                    result = val;
                } else {
                    equation += " * ";
                    result *= val.floatValue();
                }
                equation += String.valueOf(val.floatValue());

            }
        }
        saveEquation(equation, result);
        return result;
    }


    /**
     * Saves the Calculation results to the Database
     *
     * @param equation String readable Equation of Calculation
     * @param result   float result of calculated Equation
     * @return boolean if save was successful
     */
    private boolean saveEquation(String equation, float result) {
        //We only want to track if there was actually a calculation made
        if (StringUtils.hasText(equation)) {
            Calculation calculation = new Calculation();
            calculation.setEquation(equation);
            calculation.setResult(result);
            calculationRepository.save(calculation);
            return true;
        }
        return false;
    }


}
