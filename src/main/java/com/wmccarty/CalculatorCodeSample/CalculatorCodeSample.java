package com.wmccarty.CalculatorCodeSample;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : CalculatorCodeSample Application Entry Point
 **/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CalculatorCodeSample {
    /**
     * Main function of Application
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CalculatorCodeSample.class, args);
    }
}
