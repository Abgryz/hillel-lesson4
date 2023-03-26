package ithillel.lesson13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {

    @Test
    void squareSum() {
        assertEquals(SimpleCalculator.squareSum(10, 20), 500);
    }
}