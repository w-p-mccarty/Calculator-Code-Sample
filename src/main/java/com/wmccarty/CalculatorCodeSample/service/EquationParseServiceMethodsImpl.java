package com.wmccarty.CalculatorCodeSample.service;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Implementation of EquationParseServiceMethods that handles parsing actions of Equation
 **/

import com.wmccarty.CalculatorCodeSample.entity.Equation;
import com.wmccarty.CalculatorCodeSample.repositories.EquationRepository;
import com.wmccarty.CalculatorCodeSample.utils.TermUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class EquationParseServiceMethodsImpl implements EquationParseServiceMethods {

    @Autowired
    private EquationRepository equationRepository;
    private final String MULT_DIV = "MULT_DIV";
    private final String ADD_SUB = "ADD_SUB";


    /**
     * Gets the Equation in readable format
     *
     * @param id String id of the Equation
     * @return String readable format of Equation
     */
    public String getEquation(String id) {
        String equationString = "";
        if (StringUtils.hasText(id)) {
            Equation equation = equationRepository.findById(id).orElse(null);
            if (equation == null || (equation != null && equation.getTerms().size() == 0)) {
                return equationString;
            }

            equationString = TermUtils.getEquation(equation);
        }
        return equationString;
    }

    /**
     * Evaluates Equation
     *
     * @param id String id of Equation to evaluate
     * @return float result of evaluated Equation
     */
    public float evalEquation(String id) {

        float result = 0f;
        if (StringUtils.hasText(id)) {
            Equation equation = equationRepository.findById(id).orElse(null);
            //We only are going to process valid equations, otherwise it will result 0
            if (equation == null || (equation != null && equation.getTerms().size() == 0) || !isValid(equation)) {
                return result;
            }

            //We aren't wanting to mess with the actual object references
            List<Equation.Term> totalTerms = cloneTerms(equation);
            //in PEMDAS Order
            String currentOperation = currentOperation(totalTerms);
            //Safety buffer
            long maxBuffer = 100;
            long bufferCount = 0;
            while (totalTerms.size() != 1 || bufferCount >= maxBuffer) {
                //We process in sets of 3 terms ie (1+2)
                for (int i = 0; i + 2 < totalTerms.size(); ) {
                    //Get those 3 terms
                    Equation.Term lhs = totalTerms.get(i);
                    Equation.Term operator = totalTerms.get(i + 1);
                    Equation.Term rhs = totalTerms.get(i + 2);
                    //See if this is the correct operator we are processing
                    if (!isValidOperator(operator.getValue(), currentOperation)) {
                        //its not so move to the next term
                        //ie in the equation 1 + 2 * 3 we try 1 + 2, but we are doing *, so it will move to 2 * 3
                        i += 2;
                        continue;
                    }

                    //This is the operator we are doing to calculate it
                    float newValue = calculate(lhs.getValue(), operator.getValue(), rhs.getValue());
                    //Set lhs term to be this new value
                    lhs.setValue(String.valueOf(newValue));
                    //Remove the operator and rhs terms
                    totalTerms.remove(i + 2);
                    totalTerms.remove(i + 1);
                }
                //Update operation order
                currentOperation = currentOperation(totalTerms);
                bufferCount++;
            }
            //The list should contain 1 item as everything merges into that lhs element - which is the result
            result = totalTerms.size() == 1 ? Float.parseFloat(totalTerms.get(0).getValue()) : 0f;

        }
        return result;
    }

    /**
     * Determines if the Equation is in a Valid State to Evaluate
     *
     * @param equation Equation being validated
     * @return boolean whether the Equation is in a valid state
     */
    private boolean isValid(Equation equation) {

        if (equation != null && equation.getTerms().size() > 0) {
            boolean lastElementIsNumber = false;
            for (int i = 0; i < equation.getTerms().size(); i++) {
                Equation.Term term = equation.getTerms().get(i);
                if (i % 2 == 0 && term.isOperation()) {
                    //Even Number indexes should be numbers
                    return false;
                } else if (i % 2 != 0 && !term.isOperation()) {
                    //Odd Number indexes should be operators
                    return false;
                }

                if (!term.isOperation() && !TermUtils.isNumeric(term.getValue())) {
                    //Terms that are numbers shouldn't be operators
                    return false;
                } else if (term.isOperation() && TermUtils.isNumeric(term.getValue())) {
                    //Terms that are operators shouldn't be numeric
                    return false;
                }
                lastElementIsNumber = !term.isOperation() && TermUtils.isNumeric(term.getValue());
            }
            // The last term must be a number
            if (!lastElementIsNumber) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Clones Values of Equation Term
     *
     * @param equation
     * @return
     */
    private List<Equation.Term> cloneTerms(Equation equation) {
        List<Equation.Term> terms = new ArrayList<>();
        for (Equation.Term term : equation.getTerms()) {
            Equation.Term clone = new Equation.Term();
            clone.setTermId(term.getTermId());
            clone.setOperation(term.isOperation());
            clone.setValue(term.getValue());
            terms.add(clone);
        }
        return terms;
    }

    /**
     * Determines if Operator is valid
     *
     * @param operator      String being checked
     * @param validOperator String that is Operator type currently on
     * @return boolean if operator is Valid
     */
    private boolean isValidOperator(String operator, String validOperator) {
        //PEMDAS processes left to right
        if (validOperator.equals(MULT_DIV)) {
            //Multiplication and Division process left to right at same time
            return operator.equals("*") || operator.equals("/");
        } else if (validOperator.equals(ADD_SUB)) {
            //Addition and Subtraction process left to right at same time
            return operator.equals("+") || operator.equals("-");
        }
        return false;
    }

    /**
     * Determine what operation of the List of the list of Terms should be on
     *
     * @param terms List<Term> Equation terms being evaluated
     * @return String currentOperation that should be processed next
     */
    private String currentOperation(List<Equation.Term> terms) {
        //Start with Multiplication
        String currentOperation = MULT_DIV;
        long count = terms.stream()
                .filter(o -> o.getValue().equals("*"))
                .count();
        //If no Multiplication move to Division
        if (count == 0) {
            count = terms.stream()
                    .filter(o -> o.getValue().equals("/"))
                    .count();
        }
        //If no Division move to Addition
        if (count == 0) {
            currentOperation = ADD_SUB;
            count = terms.stream()
                    .filter(o -> o.getValue().equals("+"))
                    .count();
        }
        //If no Addition move to Subtraction
        if (count == 0) {
            currentOperation = ADD_SUB;
            count = terms.stream()
                    .filter(o -> o.getValue().equals("-"))
                    .count();
        }
        if (count == 0) {
            //No More Operations
            currentOperation = "";
        }
        return currentOperation;
    }

    /**
     * Calculates a set of values
     *
     * @param lhs      String Left hand side of Set
     * @param operator String operator of set
     * @param rhs      String Right hand side of Set
     * @return float result of the calculation of the set
     */
    private float calculate(String lhs, String operator, String rhs) {
        float result = 0f;
        //Since this might be the start of a negative value, we don't actually have a number, so its invalid
        if (lhs.equals("-") || rhs.equals("-")) {
            return 0f;
        }


        if (operator.equals("+")) {
            //Sum
            result = Float.parseFloat(lhs) + Float.parseFloat(rhs);
        } else if (operator.equals("-")) {
            //Subtraction
            result = Float.parseFloat(lhs) - Float.parseFloat(rhs);
        } else if (operator.equals("*")) {
            //Multiplication
            result = Float.parseFloat(lhs) * Float.parseFloat(rhs);
        } else if (operator.equals("/")) {
            //Division
            float denominator = Float.parseFloat(rhs);
            //Protection of / by 0
            if (denominator != 0f) {
                result = Float.parseFloat(lhs) / Float.parseFloat(rhs);
            }
        }
        return result;
    }
}
