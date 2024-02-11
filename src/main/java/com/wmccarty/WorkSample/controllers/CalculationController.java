package com.wmccarty.WorkSample.controllers;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Controller for handling Calculation Service Actions
 **/

import com.wmccarty.WorkSample.api.requests.DivValuesRequest;
import com.wmccarty.WorkSample.api.requests.MultValuesRequest;
import com.wmccarty.WorkSample.api.requests.SubValuesRequest;
import com.wmccarty.WorkSample.api.requests.SumValuesRequest;
import com.wmccarty.WorkSample.api.responses.DivValuesResponse;
import com.wmccarty.WorkSample.api.responses.MultValuesResponse;
import com.wmccarty.WorkSample.api.responses.SubValuesResponse;
import com.wmccarty.WorkSample.api.responses.SumValuesResponse;
import com.wmccarty.WorkSample.service.CalculationServiceMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class CalculationController {

    @Autowired
    private CalculationServiceMethods calculationServiceMethods;

    /**
     * Sums the values in the request and gives the result
     *
     * @param sumValuesRequest Values that will be summed
     * @return float result of the list of values
     */
    @PostMapping("/sum-values")
    public ResponseEntity<SumValuesResponse> processSumValuesRequest(@RequestBody SumValuesRequest sumValuesRequest) {
        log.info("Request ============ SumValuesRequest");
        float result = calculationServiceMethods.sumValues(sumValuesRequest.getValues());
        SumValuesResponse sumValuesResponse = new SumValuesResponse();
        sumValuesResponse.setResult(result);

        return new ResponseEntity<>(sumValuesResponse, HttpStatus.OK);
    }

    /**
     * Subtracts the values in the request and gives the result
     *
     * @param subValuesRequest Values that will be Subtracted
     * @return float result of the list of values
     */
    @PostMapping("/sub-values")
    public ResponseEntity<SubValuesResponse> processSubValuesRequest(@RequestBody SubValuesRequest subValuesRequest) {
        log.info("Request ============ SubValuesRequest");
        float result = calculationServiceMethods.subValues(subValuesRequest.getValues());
        SubValuesResponse subValuesResponse = new SubValuesResponse();
        subValuesResponse.setResult(result);

        return new ResponseEntity<>(subValuesResponse, HttpStatus.OK);
    }

    /**
     * Divides the values in the request and gives the result
     *
     * @param divValuesRequest Values that will be Divided
     * @return float result of the list of values
     */
    @PostMapping("/div-values")
    public ResponseEntity<DivValuesResponse> processDivValuesRequest(@RequestBody DivValuesRequest divValuesRequest) {
        log.info("Request ============ DivValuesRequest");
        float result = calculationServiceMethods.divValues(divValuesRequest.getValues());
        DivValuesResponse divValuesResponse = new DivValuesResponse();
        divValuesResponse.setResult(result);

        return new ResponseEntity<>(divValuesResponse, HttpStatus.OK);
    }

    /**
     * Multiplies the values in the request and gives the result
     *
     * @param multValuesRequest Values that will be Multiplied
     * @return float result of the list of values
     */
    @PostMapping("/mult-values")
    public ResponseEntity<MultValuesResponse> processMultValuesRequest(@RequestBody MultValuesRequest multValuesRequest) {
        log.info("Request ============ MultValuesRequest");
        float result = calculationServiceMethods.multValues(multValuesRequest.getValues());
        MultValuesResponse multValuesResponse = new MultValuesResponse();
        multValuesResponse.setResult(result);

        return new ResponseEntity<>(multValuesResponse, HttpStatus.OK);
    }
}
