package com.wmccarty.CalculatorCodeSample;
/**
 * @author : William McCarty
 * @mailto : wpmccarty@yahoo.com
 * @summary : Unit Tests for Equation Services Methods
 **/

import com.wmccarty.CalculatorCodeSample.entity.Equation;
import com.wmccarty.CalculatorCodeSample.repositories.EquationRepository;
import com.wmccarty.CalculatorCodeSample.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@DataMongoTest
@AutoConfigureMockMvc
public class EquationServiceMethodsTest {

    @Mock
    private EquationRepository equationRepository;
    @InjectMocks
    private EquationParseServiceMethodsImpl equationParseServiceMethods;

    @InjectMocks
    private EquationServiceMethodsImpl equationServiceMethods;

    /**
     * Preps Mockito
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests Adding Terms to an Equation
     */
    @Test
    public void testAddToEquation() {
        log.info("testAddToEquation Started");

        Equation savedEquation = new Equation();
        savedEquation.setId("1");
        savedEquation.setEquation("");

        when(equationRepository.findById("1")).thenReturn(Optional.of(savedEquation));
        when(equationRepository.save(Mockito.any(Equation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //Test a basic Equation
        equationServiceMethods.addToEquation("1", "1");
        equationServiceMethods.addToEquation("1", "2");
        equationServiceMethods.addToEquation("1", "3");
        equationServiceMethods.addToEquation("1", "+");
        equationServiceMethods.addToEquation("1", "1");
        Equation equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        String expectedValue = "123 + 1";
        log.info("Test #1: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);


        //Reset Equation
        savedEquation = new Equation();
        savedEquation.setId("1");
        savedEquation.setEquation("");
        when(equationRepository.findById("1")).thenReturn(Optional.of(savedEquation));

        //Test that numbers and operations can't be added at the same time
        equationServiceMethods.addToEquation("1", "1++");
        equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        expectedValue = "";
        log.info("Test #2: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);

        //Test at start is allowed for negative numbers
        equationServiceMethods.addToEquation("1", "-");
        equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        expectedValue = "-";
        log.info("Test #3: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);

        //Test number is combined with - sign to make it negative
        equationServiceMethods.addToEquation("1", "1");
        equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        expectedValue = "-1";
        log.info("Test #4: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);

        //Test adding normal operation
        equationServiceMethods.addToEquation("1", "-");
        equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        expectedValue = "-1 -";
        log.info("Test #5: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);

        equationServiceMethods.addToEquation("1", "5");
        equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        expectedValue = "-1 - 5";
        log.info("Test #6: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);

        equationServiceMethods.addToEquation("1", "*");
        equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        expectedValue = "-1 - 5 *";
        log.info("Test #7: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);

        //Test adding a negative value after an operation and its allowed
        equationServiceMethods.addToEquation("1", "-");
        equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        expectedValue = "-1 - 5 * -";
        log.info("Test #8: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);

        //Test adding another operation after the - isn't allowed
        equationServiceMethods.addToEquation("1", "-");
        equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        expectedValue = "-1 - 5 * -";
        log.info("Test #9: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);

        equationServiceMethods.addToEquation("1", "3");
        equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        expectedValue = "-1 - 5 * -3";
        log.info("Test #9: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);

        log.info("testAddToEquation Finished");
    }

    /**
     * Tests Evaluating Equations
     */
    @Test
    public void testEvalEquation() {
        log.info("testEvalEquation Started");


        Equation savedEquation = new Equation();
        savedEquation.setId("1");

        when(equationRepository.findById("1")).thenReturn(Optional.of(savedEquation));
        when(equationRepository.save(Mockito.any(Equation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //Test evaluating an equation with all operators
        equationServiceMethods.addToEquation("1", "1");
        equationServiceMethods.addToEquation("1", "+");
        equationServiceMethods.addToEquation("1", "2");
        equationServiceMethods.addToEquation("1", "-");
        equationServiceMethods.addToEquation("1", "3");
        equationServiceMethods.addToEquation("1", "*");
        equationServiceMethods.addToEquation("1", "4");
        equationServiceMethods.addToEquation("1", "/");
        equationServiceMethods.addToEquation("1", "5");
        Equation equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        String expectedValue = "1 + 2 - 3 * 4 / 5";
        log.info("Test #1: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);
        float result = equationParseServiceMethods.evalEquation("1");
        assertEquals(result, .6f, .01);

        //Reset Equation
        savedEquation = new Equation();
        savedEquation.setId("1");
        when(equationRepository.findById("1")).thenReturn(Optional.of(savedEquation));

        //Test trying to evaluation an incomplete equation
        equationServiceMethods.addToEquation("1", "1");
        equationServiceMethods.addToEquation("1", "+");
        equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        expectedValue = "1 +";
        log.info("Test #2: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);
        result = equationParseServiceMethods.evalEquation("1");
        float expectedResult = 0f;
        log.info("Test #3: Expected: " + expectedResult + " Equation Value: " + result + " Equation: " + equation.getEquation());
        assertEquals(result, expectedResult, 0);

        //Normal Test
        equationServiceMethods.addToEquation("1", "2");
        equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        expectedValue = "1 + 2";
        log.info("Test #4: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);
        result = equationParseServiceMethods.evalEquation("1");
        expectedResult = 3f;
        log.info("Test #5: Expected: " + expectedResult + " Equation Value: " + result + " Equation: " + equation.getEquation());
        assertEquals(result, expectedResult, 0);

        //Test Doing multiple of the same operations in the same equation
        equationServiceMethods.addToEquation("1", "-");
        equationServiceMethods.addToEquation("1", "3");
        equationServiceMethods.addToEquation("1", "*");
        equationServiceMethods.addToEquation("1", "4");
        equationServiceMethods.addToEquation("1", "/");
        equationServiceMethods.addToEquation("1", "5");
        equationServiceMethods.addToEquation("1", "+");
        equationServiceMethods.addToEquation("1", "1");
        equationServiceMethods.addToEquation("1", "-");
        equationServiceMethods.addToEquation("1", "2");
        equationServiceMethods.addToEquation("1", "*");
        equationServiceMethods.addToEquation("1", "3");
        equationServiceMethods.addToEquation("1", "/");
        equationServiceMethods.addToEquation("1", "4");
        equationServiceMethods.addToEquation("1", "+");
        equationServiceMethods.addToEquation("1", "5");
        equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        expectedValue = "1 + 2 - 3 * 4 / 5 + 1 - 2 * 3 / 4 + 5";
        log.info("Test #6: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);
        result = equationParseServiceMethods.evalEquation("1");
        expectedResult = 5.1f;
        log.info("Test #7: Expected: " + expectedResult + " Equation Value: " + result + " Equation: " + equation.getEquation());
        assertEquals(result, expectedResult, 0);

        //Test an incomplete equation
        equationServiceMethods.addToEquation("1", "-");
        equation = equationRepository.findById("1").orElse(null);
        assertNotNull(equation);
        expectedValue = "1 + 2 - 3 * 4 / 5 + 1 - 2 * 3 / 4 + 5 -";
        log.info("Test #8: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);
        result = equationParseServiceMethods.evalEquation("1");
        expectedResult = 0f;
        log.info("Test #9: Expected: " + expectedResult + " Equation Value: " + result + " Equation: " + equation.getEquation());
        assertEquals(result, expectedResult, 0);

        log.info("testEvalEquation Finished");
    }

    /**
     * Tests Printing Equations
     */
    @Test
    public void testPrintEquation() {
        log.info("testPrintEquation Started");


        Equation savedEquation = new Equation();
        savedEquation.setId("1");

        when(equationRepository.findById("1")).thenReturn(Optional.of(savedEquation));
        when(equationRepository.save(Mockito.any(Equation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //Test normal equation
        equationServiceMethods.addToEquation("1", "1");
        equationServiceMethods.addToEquation("1", "+");
        equationServiceMethods.addToEquation("1", "2");
        equationServiceMethods.addToEquation("1", "-");
        equationServiceMethods.addToEquation("1", "3");
        equationServiceMethods.addToEquation("1", "*");
        equationServiceMethods.addToEquation("1", "4");
        equationServiceMethods.addToEquation("1", "/");
        equationServiceMethods.addToEquation("1", "5");
        String expectedValue = "1 + 2 - 3 * 4 / 5";
        String result = equationParseServiceMethods.getEquation("1");
        log.info("Test #1: Expected: " + expectedValue + " Equation Value: " + result);
        assertEquals(result, expectedValue);

        //Reset Equation
        savedEquation = new Equation();
        savedEquation.setId("1");

        //Test impartial equations printing
        when(equationRepository.findById("1")).thenReturn(Optional.of(savedEquation));
        equationServiceMethods.addToEquation("1", "1");
        equationServiceMethods.addToEquation("1", "+");
        result = equationParseServiceMethods.getEquation("1");
        expectedValue = "1 +";
        log.info("Test #2: Expected: " + expectedValue + " Equation Value: " + result);
        assertEquals(result, expectedValue);

        //Test Normal
        equationServiceMethods.addToEquation("1", "2");
        result = equationParseServiceMethods.getEquation("1");
        expectedValue = "1 + 2";
        log.info("Test #3: Expected: " + expectedValue + " Equation Value: " + result);
        assertEquals(result, expectedValue);

        //Test Long equation
        equationServiceMethods.addToEquation("1", "-");
        equationServiceMethods.addToEquation("1", "3");
        equationServiceMethods.addToEquation("1", "*");
        equationServiceMethods.addToEquation("1", "4");
        equationServiceMethods.addToEquation("1", "/");
        equationServiceMethods.addToEquation("1", "5");
        equationServiceMethods.addToEquation("1", "+");
        equationServiceMethods.addToEquation("1", "1");
        equationServiceMethods.addToEquation("1", "-");
        equationServiceMethods.addToEquation("1", "2");
        equationServiceMethods.addToEquation("1", "*");
        equationServiceMethods.addToEquation("1", "3");
        equationServiceMethods.addToEquation("1", "/");
        equationServiceMethods.addToEquation("1", "4");
        equationServiceMethods.addToEquation("1", "+");
        equationServiceMethods.addToEquation("1", "5");
        result = equationParseServiceMethods.getEquation("1");
        expectedValue = "1 + 2 - 3 * 4 / 5 + 1 - 2 * 3 / 4 + 5";
        log.info("Test #4: Expected: " + expectedValue + " Equation Value: " + result);
        assertEquals(result, expectedValue);


        log.info("testPrintEquation Finished");
    }


    /**
     * Test Deleting of Equations
     */
    @Test
    public void testClearEquation() {
        log.info("testClearEquation Started");


        Equation savedEquation = new Equation();
        savedEquation.setId("1");

        when(equationRepository.findById("1")).thenReturn(Optional.of(savedEquation));
        when(equationRepository.save(Mockito.any(Equation.class))).thenAnswer(invocation -> invocation.getArgument(0));
        doNothing().when(equationRepository).deleteById(anyString());

        //Tests normal removal
        equationServiceMethods.addToEquation("1", "1");
        equationServiceMethods.addToEquation("1", "+");
        equationServiceMethods.addToEquation("1", "2");
        equationServiceMethods.addToEquation("1", "-");
        equationServiceMethods.addToEquation("1", "3");
        equationServiceMethods.addToEquation("1", "*");
        equationServiceMethods.addToEquation("1", "4");
        equationServiceMethods.addToEquation("1", "/");
        equationServiceMethods.addToEquation("1", "5");
        String expectedValue = "1 + 2 - 3 * 4 / 5";
        Equation equation = equationRepository.findById("1").orElse(null);
        log.info("Test #1: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);
        equationServiceMethods.clearEquation("1");
        verify(equationRepository).deleteById("1");
        log.info("Test #2: Expected Deleted");

        //Reset Equation - set to Id 2 for Mockito
        savedEquation = new Equation();
        savedEquation.setId("2");

        //Test Deleting long Equation
        when(equationRepository.findById("2")).thenReturn(Optional.of(savedEquation));
        equationServiceMethods.addToEquation("2", "1");
        equationServiceMethods.addToEquation("2", "+");
        equationServiceMethods.addToEquation("2", "2");
        equationServiceMethods.addToEquation("2", "-");
        equationServiceMethods.addToEquation("2", "3");
        equationServiceMethods.addToEquation("2", "*");
        equationServiceMethods.addToEquation("2", "4");
        equationServiceMethods.addToEquation("2", "/");
        equationServiceMethods.addToEquation("2", "5");
        equationServiceMethods.addToEquation("2", "+");
        equationServiceMethods.addToEquation("2", "1");
        equationServiceMethods.addToEquation("2", "-");
        equationServiceMethods.addToEquation("2", "2");
        equationServiceMethods.addToEquation("2", "*");
        equationServiceMethods.addToEquation("2", "3");
        equationServiceMethods.addToEquation("2", "/");
        equationServiceMethods.addToEquation("2", "4");
        equationServiceMethods.addToEquation("2", "+");
        equationServiceMethods.addToEquation("2", "5");
        expectedValue = "1 + 2 - 3 * 4 / 5 + 1 - 2 * 3 / 4 + 5";
        equation = equationRepository.findById("2").orElse(null);
        log.info("Test #3: Expected: " + expectedValue + " Equation Value: " + equation.getEquation());
        assertEquals(equation.getEquation(), expectedValue);
        equationServiceMethods.clearEquation("2");
        verify(equationRepository).deleteById("2");
        log.info("Test #4: Expected Deleted");

        log.info("testClearEquation Finished");
    }
}
