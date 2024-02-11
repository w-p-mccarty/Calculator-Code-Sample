package com.wmccarty.WorkSample;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : WorkSample Application Entry Point
 **/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class WorkSample {
    /**
     * Main function of Application
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(WorkSample.class, args);
    }
}
