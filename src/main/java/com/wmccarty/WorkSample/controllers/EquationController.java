package com.wmccarty.WorkSample.controllers;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Controller for handling Equation Service Actions
 **/

import com.wmccarty.WorkSample.api.requests.*;
import com.wmccarty.WorkSample.api.responses.*;
import com.wmccarty.WorkSample.service.EquationParseServiceMethods;
import com.wmccarty.WorkSample.service.EquationServiceMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EquationController {

    @Autowired
    private EquationServiceMethods equationServiceMethods;

    @Autowired
    private EquationParseServiceMethods equationParseServiceMethods;

    /**
     * Adds Terms to a Equation
     *
     * @param addToEquationRequest Equation that the term will be added to
     * @return boolean successful if the addition occurred
     */
    @PostMapping("/add-to-equation")
    public ResponseEntity<AddToEquationResponse> processAddToEquationRequest(@RequestBody AddToEquationRequest addToEquationRequest) {
        log.info("Request ============ AddToEquationRequest");
        boolean result = equationServiceMethods.addToEquation(addToEquationRequest.getId(), addToEquationRequest.getTerm());
        AddToEquationResponse addToEquationResponse = new AddToEquationResponse();
        addToEquationResponse.setSuccess(result);

        return new ResponseEntity<>(addToEquationResponse, HttpStatus.OK);
    }

    /**
     * Prints the Equation that is currently stored in the Database
     *
     * @param printEquationRequest Equation that is wanting to be printed
     * @return String form of the Equation
     */
    @GetMapping("/print-equation")
    public ResponseEntity<PrintEquationResponse> processPrintEquationRequest(@RequestBody PrintEquationRequest printEquationRequest) {
        log.info("Request ============ PrintEquationRequest");
        String result = equationParseServiceMethods.getEquation(printEquationRequest.getId());
        PrintEquationResponse printEquationResponse = new PrintEquationResponse();
        printEquationResponse.setEquation(result);

        return new ResponseEntity<>(printEquationResponse, HttpStatus.OK);
    }

    /**
     * Evaluates the Equation that is in the Database
     *
     * @param evalEquationRequest Equation that is being evaluated
     * @return float result of the processed Equation
     */
    @GetMapping("/eval-equation")
    public ResponseEntity<EvalEquationResponse> processEvalEquationRequest(@RequestBody EvalEquationRequest evalEquationRequest) {
        log.info("Request ============ EvalEquationRequest");
        float result = equationParseServiceMethods.evalEquation(evalEquationRequest.getId());
        EvalEquationResponse sumValuesResponse = new EvalEquationResponse();
        sumValuesResponse.setResult(result);

        return new ResponseEntity<>(sumValuesResponse, HttpStatus.OK);
    }

    /**
     * Deletes the Equation from the Database
     *
     * @param clearEquationRequest The Equation to be deleted
     * @return boolean if delete was successful
     */
    @PostMapping("/clear-equation")
    public ResponseEntity<ClearEquationResponse> processClearEquationRequest(@RequestBody ClearEquationRequest clearEquationRequest) {
        log.info("Request ============ ClearEquationRequest");
        boolean result = equationServiceMethods.clearEquation(clearEquationRequest.getId());
        ClearEquationResponse clearEquationResponse = new ClearEquationResponse();
        clearEquationResponse.setSuccess(result);

        return new ResponseEntity<>(clearEquationResponse, HttpStatus.OK);
    }
}
