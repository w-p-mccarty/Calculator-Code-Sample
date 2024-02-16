package com.wmccarty.CalculatorCodeSample.entity;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Calculations for equations and results to track history
 **/

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document("calculation")
public class Calculation {
    @Id
    @Column(name = "id", length = 45)
    @Indexed(unique = true)
    private String id;
    private String equation;
    private float result;

    /**
     * Gets Id of the Calculation
     *
     * @return String Id of Calculation
     */
    @JsonGetter("Id")
    public String getId() {
        return id;
    }

    /**
     * Sets Id of the Calculation
     *
     * @param id String Id of Calculation
     */
    @JsonSetter("Id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets String equation of Calculation
     *
     * @return String form of Calculation
     */
    @JsonGetter("Equation")
    public String getEquation() {
        return equation;
    }

    /**
     * Sets String equation of Calculation
     *
     * @param equation String form of Calculation
     */
    @JsonSetter("Equation")
    public void setEquation(String equation) {
        this.equation = equation;
    }

    /**
     * Gets result of the Calculation
     *
     * @return float result from Calculation
     */
    @JsonGetter("Result")
    public float getResult() {
        return result;
    }

    /**
     * Sets result of the Calculation
     *
     * @param result float result from Calculation
     */
    @JsonSetter("Result")
    public void setResult(float result) {
        this.result = result;
    }
}
