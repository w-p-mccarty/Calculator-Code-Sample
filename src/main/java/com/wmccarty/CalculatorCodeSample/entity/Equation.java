package com.wmccarty.CalculatorCodeSample.entity;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : An Equation of Terms that can be appended to and Evaluated
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Entity
@Document("equation")
public class Equation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 255)
    @Indexed(unique = true)
    private String id;
    @ElementCollection
    private List<Term> terms;
    private String equation;

    /**
     * Gets Id of Equation
     *
     * @return String Id of Equation
     */
    @JsonGetter("Id")
    public String getId() {
        return id;
    }

    /**
     * Sets Id of Equation
     *
     * @param id String Id of Equation
     */
    @JsonSetter("Id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets Terms of Equation
     *
     * @return List<Term> Terms of Equation
     */
    @JsonGetter("Terms")
    public List<Term> getTerms() {
        if (terms == null) {
            terms = new ArrayList<>();
        }
        return terms;
    }

    /**
     * Sets Terms of Equation
     *
     * @param terms List<Term> Terms of Equation
     */
    @JsonSetter("Terms")
    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    /**
     * Gets readable format of Equation
     *
     * @return String Equation in readable format
     */
    @JsonGetter("Equation")
    public String getEquation() {
        return equation;
    }

    /**
     * Sets Equation in readable format
     *
     * @param equation String Equation in readable format
     */
    @JsonSetter("Equation")
    public void setEquation(String equation) {
        this.equation = equation;
    }

    /**
     * This will either be a Numeric value(0-9*)
     * Or
     * a operation(+,-,*,/)
     */
    @Embeddable
    public static class Term {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "TermId", length = 255)
        @Indexed(unique = true)
        private String termId;
        private boolean isOperation;
        private String value;

        /**
         * Gets TermId of term
         *
         * @return String TermId of term
         */
        @JsonGetter("TermId")
        public String getTermId() {
            return termId;
        }

        /**
         * Sets TermId of term
         *
         * @param termId String TermId of term
         */
        @JsonSetter("TermId")
        public void setTermId(String termId) {
            this.termId = termId;
        }

        /**
         * Gets whether this term is (+,-,*,/)
         *
         * @return boolean if term is (+,-,*,/)
         */
        @JsonGetter("Operation")
        public boolean isOperation() {
            return isOperation;
        }

        /**
         * Sets whether this term is (+,-,*,/)
         *
         * @param operation boolean if term is (+,-,*,/)
         */
        @JsonSetter("Operation")
        public void setOperation(boolean operation) {
            isOperation = operation;
        }

        /**
         * Gets term value (+,-,*,/ or 0-9*)
         *
         * @return String term value (+,-,*,/ or 0-9)
         */
        @JsonGetter("Value")
        public String getValue() {
            return value;
        }

        /**
         * Sets term value (+,-,*,/ or 0-9)
         *
         * @param value String term value (+,-,*,/ or 0-9)
         */
        @JsonSetter("Value")
        public void setValue(String value) {
            this.value = value;
        }
    }
}
