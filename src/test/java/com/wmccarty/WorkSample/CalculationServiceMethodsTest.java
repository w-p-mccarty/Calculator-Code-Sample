package com.wmccarty.WorkSample;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Unit Tests for CalculationServiceMethods
 **/

import com.wmccarty.WorkSample.entity.Calculation;
import com.wmccarty.WorkSample.repositories.CalculationRepository;
import com.wmccarty.WorkSample.service.CalculationServiceMethodsImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class CalculationServiceMethodsTest {

    @Mock
    private CalculationRepository calculationRepository;
    @InjectMocks
    private CalculationServiceMethodsImpl calculationService;

    /**
     * Preps Mockito
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests Summing values together
     */
    @Test
    public void testSumValues() {
        log.info("testSumValues Started");
        when(calculationRepository.save(Mockito.any(Calculation.class))).thenAnswer(invocation -> invocation.getArgument(0));
        // Test input values
        List<Float> values = Arrays.asList(1f, 2f, 3f);

        // Call the sumValues method
        float sum = calculationService.sumValues(values);
        float expectedValue = 6f;
        // Verify that the correct sum is calculated
        assertEquals(expectedValue, sum, 0);

        //Test Normal
        values = Arrays.asList(1f, 2f, 3f, 100f);
        sum = calculationService.sumValues(values);
        expectedValue = 106f;
        log.info("Test #1: Expected: " + expectedValue + " Sum Value: " + sum);
        assertEquals(expectedValue, sum, 0);

        //Test with a Negative
        values = Arrays.asList(1f, 2f, 3f, -100f);
        sum = calculationService.sumValues(values);
        expectedValue = -94f;
        log.info("Test #2: Expected: " + expectedValue + " Sum Value: " + sum);
        assertEquals(expectedValue, sum, 0);

        //Test with Zeros
        values = Arrays.asList(1f, 2f, 3f, 0f, 0f, 0f);
        sum = calculationService.sumValues(values);
        expectedValue = 6f;
        log.info("Test #3: Expected: " + expectedValue + " Sum Value: " + sum);
        assertEquals(expectedValue, sum, 0);

        //Test with no entries
        values = Arrays.asList();
        sum = calculationService.sumValues(values);
        expectedValue = 0f;
        log.info("Test #4: Expected: " + expectedValue + " Sum Value: " + sum);
        assertEquals(expectedValue, sum, 0);

        //Test with a lot of values
        values = Arrays.asList(1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f);
        sum = calculationService.sumValues(values);
        expectedValue = 42f;
        log.info("Test #5: Expected: " + expectedValue + " Sum Value: " + sum);
        assertEquals(expectedValue, sum, 0);

        //Test with floats
        values = Arrays.asList(4.3f, 7.8f, 2.1f, 9.5f, 6.7f, 3.2f, 1.6f, 5.9f, 8.4f, 0.7f, 2.8f, 6.1f, 3.4f, 9.2f, 1.3f, 0.4f);
        sum = calculationService.sumValues(values);
        expectedValue = 73.4f;
        log.info("Test #6: Expected: " + expectedValue + " Sum Value: " + sum);
        assertEquals(expectedValue, sum, 0);
        log.info("testSumValues Finished");
    }

    /**
     * Tests Subtracting Values
     */
    @Test
    public void testSubValues() {
        log.info("testSubValues Started");
        when(calculationRepository.save(Mockito.any(Calculation.class))).thenAnswer(invocation -> invocation.getArgument(0));
        // Test input values
        List<Float> values = Arrays.asList(1f, 2f, 3f);

        // Call the subValues method
        float result = calculationService.subValues(values);
        float expectedValue = -4f;
        // Verify that the correct sub is calculated
        assertEquals(expectedValue, result, 0);

        //Test Normal
        values = Arrays.asList(1f, 2f, 3f, 100f);
        result = calculationService.subValues(values);
        expectedValue = -104f;
        log.info("Test #1: Expected: " + expectedValue + " Sub Value: " + result);
        assertEquals(expectedValue, result, 0);

        //Test with Negative
        values = Arrays.asList(1f, 2f, 3f, -100f);
        result = calculationService.subValues(values);
        expectedValue = 96f;
        log.info("Test #2: Expected: " + expectedValue + " Sub Value: " + result);
        assertEquals(expectedValue, result, 0);

        //Test with Zeros
        values = Arrays.asList(1f, 2f, 3f, 0f, 0f, 0f);
        result = calculationService.subValues(values);
        expectedValue = -4f;
        log.info("Test #3: Expected: " + expectedValue + " Sub Value: " + result);
        assertEquals(expectedValue, result, 0);

        //Test with empty list
        values = Arrays.asList();
        result = calculationService.subValues(values);
        expectedValue = 0f;
        log.info("Test #4: Expected: " + expectedValue + " Sub Value: " + result);
        assertEquals(expectedValue, result, 0);

        //Test with a lot of values
        values = Arrays.asList(1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f);
        result = calculationService.subValues(values);
        expectedValue = -40f;
        log.info("Test #5: Expected: " + expectedValue + " Sub Value: " + result);
        assertEquals(expectedValue, result, 0);

        //Test with floats
        values = Arrays.asList(4.3f, 7.8f, 2.1f, 9.5f, 6.7f, 3.2f, 1.6f, 5.9f, 8.4f, 0.7f, 2.8f, 6.1f, 3.4f, 9.2f, 1.3f, 0.4f);
        result = calculationService.subValues(values);
        expectedValue = -64.8f;
        log.info("Test #6: Expected: " + expectedValue + " Sub Value: " + result);
        assertEquals(expectedValue, result, 0);
        log.info("testSubValues Finished");
    }

    /**
     * Tests Dividing Values
     */
    @Test
    public void testDivValues() {
        log.info("testDivValues Started");
        when(calculationRepository.save(Mockito.any(Calculation.class))).thenAnswer(invocation -> invocation.getArgument(0));
        // Test input values
        List<Float> values = Arrays.asList(1f, 2f, 3f);

        // Call the divValues method
        float result = calculationService.divValues(values);
        float expectedValue = .1667f;
        // Verify that the correct div is calculated
        assertEquals(expectedValue, result, 0.01);

        //Test Normal
        values = Arrays.asList(1f, 2f, 3f, 100f);
        result = calculationService.divValues(values);
        expectedValue = .0017f;
        log.info("Test #1: Expected: " + expectedValue + " Div Value: " + result);
        assertEquals(expectedValue, result, 0.0001);

        //Test with Negative
        values = Arrays.asList(1f, 2f, 3f, -100f);
        result = calculationService.divValues(values);
        expectedValue = -.00167f;
        log.info("Test #2: Expected: " + expectedValue + " Div Value: " + result);
        assertEquals(expectedValue, result, 0.0001);

        //Test with Zeros
        values = Arrays.asList(1f, 2f, 3f, 0f, 0f, 0f);
        result = calculationService.divValues(values);
        expectedValue = 0f;
        log.info("Test #3: Expected: " + expectedValue + " Div Value: " + result);
        assertEquals(expectedValue, result, 0);

        //Test Empty List
        values = Arrays.asList();
        result = calculationService.divValues(values);
        expectedValue = 0f;
        log.info("Test #4: Expected: " + expectedValue + " Div Value: " + result);
        assertEquals(expectedValue, result, 0);

        //Test a log of values
        values = Arrays.asList(1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f);
        result = calculationService.divValues(values);
        expectedValue = .0001f;
        log.info("Test #5: Expected: " + expectedValue + " Div Value: " + result);
        assertEquals(expectedValue, result, .0001);

        //Test floats
        values = Arrays.asList(4.3f, 7.8f, 2.1f, 9.5f, 6.7f, 3.2f, 1.6f);
        result = calculationService.divValues(values);
        expectedValue = .0008f;
        log.info("Test #6: Expected: " + expectedValue + " Div Value: " + result);
        assertEquals(expectedValue, result, .0001);
        log.info("testDivValues Finished");
    }

    /**
     * Tests Multiplying Values
     */
    @Test
    public void testMultValues() {
        log.info("testMultValues Started");
        when(calculationRepository.save(Mockito.any(Calculation.class))).thenAnswer(invocation -> invocation.getArgument(0));
        // Test input values
        List<Float> values = Arrays.asList(1f, 2f, 3f);

        // Call the multValues method
        float result = calculationService.multValues(values);
        float expectedValue = 6f;
        // Verify that the correct mult is calculated
        assertEquals(expectedValue, result, 0);

        //Test Normal
        values = Arrays.asList(1f, 2f, 3f, 100f);
        result = calculationService.multValues(values);
        expectedValue = 600f;
        log.info("Test #1: Expected: " + expectedValue + " Mult Value: " + result);
        assertEquals(expectedValue, result, 0);

        //Test Negative
        values = Arrays.asList(1f, 2f, 3f, -100f);
        result = calculationService.multValues(values);
        expectedValue = -600f;
        log.info("Test #2: Expected: " + expectedValue + " Mult Value: " + result);
        assertEquals(expectedValue, result, 0);

        //Test with Zeros
        values = Arrays.asList(1f, 2f, 3f, 0f, 0f, 0f);
        result = calculationService.multValues(values);
        expectedValue = 0f;
        log.info("Test #3: Expected: " + expectedValue + " Mult Value: " + result);
        assertEquals(expectedValue, result, 0);

        //Test with empty list
        values = Arrays.asList();
        result = calculationService.multValues(values);
        expectedValue = 0f;
        log.info("Test #4: Expected: " + expectedValue + " Mult Value: " + result);
        assertEquals(expectedValue, result, 0);

        //Test with a lot of values
        values = Arrays.asList(1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f, 1f, 2f, 3f);
        result = calculationService.multValues(values);
        expectedValue = 279936f;
        log.info("Test #5: Expected: " + expectedValue + " Mult Value: " + result);
        assertEquals(expectedValue, result, 0);

        //Test with floats
        values = Arrays.asList(4.3f, 7.8f, 2.1f, 9.5f, 6.7f, 3.2f, 1.6f, 5.9f, 8.4f, 0.7f);
        result = calculationService.multValues(values);
        expectedValue = 796306.0625f;
        log.info("Test #6: Expected: " + expectedValue + " Mult Value: " + result);
        assertEquals(expectedValue, result, .00001);
        log.info("testMultValues Finished");
    }
}
