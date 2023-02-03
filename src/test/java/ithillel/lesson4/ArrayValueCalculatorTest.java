package ithillel.lesson4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayValueCalculatorTest {

    @Test
    void doCalcTest1() {
        String[][] input = {{"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}};
        assertEquals(16, ArrayValueCalculator.doCalc(input));
    }

    @Test
    void doCalcTest2() {
        String[][] input = {{"1", "1", "1q", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}};
        assertThrows(NumberFormatException.class, ()->ArrayValueCalculator.doCalc(input));
    }

    @Test
    void doCalcTest3() {
        String[][] input = {{"1", "1", "1"}, {"1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}};
        assertThrows(ArraySizeException.class, ()->ArrayValueCalculator.doCalc(input));
    }

    @Test
    void doCalcTest4() {
        String[][] input = {{"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}};
        assertThrows(ArraySizeException.class, ()->ArrayValueCalculator.doCalc(input));
    }
}