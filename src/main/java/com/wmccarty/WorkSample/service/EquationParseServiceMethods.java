package com.wmccarty.WorkSample.service;

/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Interface for Equation Parsing Services
 **/
public interface EquationParseServiceMethods {

    /**
     * Gets the Equation in readable format
     *
     * @param id String id of the Equation
     * @return String readable format of Equation
     */
    String getEquation(String id);

    /**
     * Evaluates Equation
     *
     * @param id String id of Equation to evaluate
     * @return float result of evaluated Equation
     */
    float evalEquation(String id);
}
