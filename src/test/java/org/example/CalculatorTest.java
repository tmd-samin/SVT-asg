package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private static Calculator calculator;

    @BeforeAll
    static void setUpBeforeAll() {
        System.out.println("Initializing resources before all tests...");
        calculator = new Calculator();
    }

    @BeforeEach
    void setUpBeforeEach() {
        System.out.println("Setting up before each test...");
    }

    @AfterEach
    void cleanUpAfterEach() {
        System.out.println("Cleaning up after each test...");
    }

    @AfterAll
    static void cleanUpAfterAll() {
        System.out.println("Cleaning up resources after all tests...");
        calculator = null;
    }

    @Test
    void testOfNumberTwo() {
        Integer result = calculator.add(10, 5);
        assertNotNull(result);
        assertEquals(15, result);

        Integer result2 = calculator.add(null, 5);
        assertNull(result2);

        Integer result3 = calculator.divide(10, 2);
        assertNotNull(result3);
        assertEquals(5, result3);

        Integer result4 = calculator.divide(10, 0);
        assertNull(result4);

        Integer result5 = calculator.add(3, 3);
        assertNotEquals(10, result5);

        Integer result6 = calculator.add(4, 4);
        assertTrue(result6 == 8);
        assertFalse(result6 == 9);

        Integer result7 = calculator.divide(9, 3);
        assertTrue(result7 == 3);
        assertFalse(result7 == 4);
    }

    @Test
    void testOfNumberThree() {
        assertThrows(ArithmeticException.class, () -> {
            if (calculator.divide(10, 0) == null) {
                throw new ArithmeticException("Division by zero is not allowed");
            }
        });

        assertDoesNotThrow(() -> {
            Integer result = calculator.divide(10, 2);
            assertNotNull(result, "Result should not be null for valid division");
            assertEquals(5, result, "10 / 2 should equal 5");
        });
    }

    @Test
    void testOfNumberFour() {
        int[] expected = {1, 2, 3, 4};
        int[] actual = {1, 2, 3, 4};

        assertArrayEquals(expected, actual);

        Calculator instance1 = calculator;
        Calculator instance2 = calculator;

        assertSame(instance1, instance2);

        List<String> expectedLines = List.of(
                "Line 1:  Test",
                "Line 2:  Test",
                "Line 3:  Test"
        );
        List<String> actualLines = List.of(
                "Line 1:  Test",
                "Line 2:  Test",
                "Line 3:  Test"
        );

        assertLinesMatch(expectedLines, actualLines);
    }

    @Test
    void testOfNumberFive() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            Integer result = calculator.add(2000, 9000);
            assertNotNull(result);
            assertEquals(3000, result);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "-1, -2, -3",
            "0, 0, 0",
            "100, 200, 300"
    })
    void testOfNumberSix(int a, int b, int expected) {
        int result = calculator.add(a, b);
        assertEquals(expected, result, String.format("Expected %d + %d to equal %d", a, b, expected));
    }

    @RepeatedTest(5)
    void testOfNumberSeven() {
        int min = 1;
        int max = 10;
        int randomNumber = calculator.generateRandomNumber(min, max);
        assertTrue(randomNumber >= min && randomNumber <= max,
                String.format("Random number %d is out of range [%d, %d]", randomNumber, min, max));
    }
}
