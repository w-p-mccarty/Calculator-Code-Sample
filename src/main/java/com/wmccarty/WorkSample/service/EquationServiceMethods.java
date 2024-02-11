package com.wmccarty.WorkSample.service;

/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Interface for Equation Service Actions
 **/
public interface EquationServiceMethods {
    /**
     * Adds Terms to Equation
     *
     * @param id      String id of the Equation to add to
     * @param newTerm String term to add to the Equation
     * @return boolean if add was successful
     */
    boolean addToEquation(String id, String newTerm);

    /**
     * Clears Equation from Database
     *
     * @param id String id of Equation to delete
     * @return boolean if delete was successful
     */
    boolean clearEquation(String id);
}
