package com.wmccarty.WorkSample.utils;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Util for Handling Term operations
 **/

import com.wmccarty.WorkSample.entity.Equation;

public class TermUtils {

    /**
     * Determines if the String is a numerical value
     *
     * @param str String value to be checked for numeric
     * @return boolean if String is a valid number
     */
    public static boolean isNumeric(String str) {
        // Check if the string is null or empty
        if (str == null || str.isEmpty()) {
            return false;
        }

        // Loop through each character in the string
        for (char ch : str.toCharArray()) {
            // Check if the character is not a digit
            if (!Character.isDigit(ch)) {
                return false;
            }
        }

        // If all characters are digits, return true
        return true;
    }

    /**
     * Determines if the String is a operation sign (+,-,*,/)
     *
     * @param str String value to be checked
     * @return boolean if String is a valid operation
     */
    public static boolean isOperation(String str) {
        return str.equals("+") || str.equals("-") || str.equals("/") || str.equals("*");
    }

    /**
     * Gets a String format of the Equation
     *
     * @param equation Equation being processed into a readable String
     * @return String readable String of Equation
     */
    public static String getEquation(Equation equation) {
        String equationString = "";
        if (equation != null && equation.getTerms().size() > 0) {
            for (Equation.Term term : equation.getTerms()) {
                equationString += term.getValue() + " ";
            }
            //We want to assure there isn't an extra space at end
            equationString = equationString.trim();
        }
        return equationString;
    }
}
