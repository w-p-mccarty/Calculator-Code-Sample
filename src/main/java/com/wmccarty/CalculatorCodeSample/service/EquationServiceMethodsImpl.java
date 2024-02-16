package com.wmccarty.CalculatorCodeSample.service;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Implementation of EquationServiceMethods that processes basic Equation Actions
 **/

import com.wmccarty.CalculatorCodeSample.entity.Equation;
import com.wmccarty.CalculatorCodeSample.repositories.EquationRepository;
import com.wmccarty.CalculatorCodeSample.utils.TermUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class EquationServiceMethodsImpl implements EquationServiceMethods {

    @Autowired
    private EquationRepository equationRepository;

    /**
     * Adds Terms to Equation
     *
     * @param id      String id of the Equation to add to
     * @param newTerm String term to add to the Equation
     * @return boolean if add was successful
     */
    public boolean addToEquation(String id, String newTerm) {
        if (StringUtils.hasText(id) && StringUtils.hasLength(newTerm)) {
            Equation equation = equationRepository.findById(id).orElse(null);
            if (equation == null) {
                //Create entry if it doesn't exist
                equation = new Equation();
                equation.setId(id);
            }

            boolean addedTerm = false;
            if (TermUtils.isNumeric(newTerm)) {
                //Term is (0-9*)
                addNewNumber(equation, newTerm);
                addedTerm = true;
            } else if (TermUtils.isOperation(newTerm)) {
                //Term is (+,-,*,/)
                addNewOperation(equation, newTerm);
                addedTerm = true;
            }

            //We only want to save if the term was actually valid
            if (addedTerm) {
                equation.setEquation(TermUtils.getEquation(equation));
                equationRepository.save(equation);
            }

            return addedTerm;
        }
        return false;
    }

    /**
     * Clears Equation from Database
     *
     * @param id String id of Equation to delete
     * @return boolean if delete was successful
     */
    public boolean clearEquation(String id) {
        if (StringUtils.hasText(id)) {
            equationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Adds a number Term to the Equation Object
     *
     * @param equation Equation to add the newTerm to
     * @param newTerm  String number term that is being added to Equation
     */
    private void addNewNumber(Equation equation, String newTerm) {
        if (equation.getTerms().size() == 0) {
            //This is the first term in the equation
            Equation.Term nextTerm = new Equation.Term();
            nextTerm.setOperation(false);
            nextTerm.setValue(newTerm);
            equation.getTerms().add(nextTerm);
            return;
        }


        //We get the last element, if it's a number we concat the new value with this one
        Equation.Term term = equation.getTerms().get(equation.getTerms().size() - 1);
        // the last term was first and it was a negative
        boolean negativeOverride = equation.getTerms().size() == 1 && term.getValue().equals("-");

        if (equation.getTerms().size() > 1) {
            //We also need to check if we have +,*,/ followed by a - ie 12 * -3
            Equation.Term secondToLastTerm = equation.getTerms().get(equation.getTerms().size() - 2);
            negativeOverride = negativeOverride || secondToLastTerm.isOperation() && !secondToLastTerm.getValue().equals("-");
        }

        if (!term.isOperation() || negativeOverride) {
            //concat to existing value, no need to create another term, just update it
            term.setValue(term.getValue() + newTerm);
            if (negativeOverride) {
                //This is now a negative number
                term.setOperation(false);
            }
        } else {
            //the previous term was a operation so were starting a new number
            Equation.Term nextTerm = new Equation.Term();
            nextTerm.setOperation(false);
            nextTerm.setValue(newTerm);
            equation.getTerms().add(nextTerm);
        }

    }

    /**
     * Adds an operator Term to the Equation Object
     *
     * @param equation Equation to add the newTerm to
     * @param newTerm  String operator term that is being added to Equation
     */
    private void addNewOperation(Equation equation, String newTerm) {
        if (equation.getTerms().size() == 0) {
            //This is the first term in the equation
            //The only case we allow it is if the user is trying to assign a negative number at the start, but it is a number not operation
            if (newTerm.equals("-")) {
                Equation.Term nextTerm = new Equation.Term();
                //This is still an operation but will be changed once we get a number
                nextTerm.setOperation(true);
                nextTerm.setValue(newTerm);
                equation.getTerms().add(nextTerm);
            }
        }

        //We get the last element
        Equation.Term term = equation.getTerms().get(equation.getTerms().size() - 1);
        if (!term.isOperation()) {
            //if last term is not an operation we create the new operation to the equation
            Equation.Term nextTerm = new Equation.Term();
            nextTerm.setOperation(true);
            nextTerm.setValue(newTerm);
            equation.getTerms().add(nextTerm);
        } else if (!term.getValue().equals("-") && newTerm.equals("-")) {
            //if the last term was an operation and not "-" and the new one is, then we allow it
            Equation.Term nextTerm = new Equation.Term();
            nextTerm.setOperation(true);
            nextTerm.setValue(newTerm);
            equation.getTerms().add(nextTerm);
        }

    }
}
