package ithillel.lesson4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayValueCalculatorTest {

    @Test
    void doCalcTest1() {
        String[][] gh = {{"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}};
        assertEquals(16, ArrayValueCalculator.doCalc(gh));
    }

    @Test
    void doCalcTest2() {
        String[][] gh = {{"1", "1", "1q", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}};
        assertThrows(NumberFormatException.class, ()->ArrayValueCalculator.doCalc(gh));
    }

    @Test
    void doCalcTest3() {
        String[][] gh = {{"1", "1", "1"}, {"1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}};
        assertThrows(ArraySizeException.class, ()->ArrayValueCalculator.doCalc(gh));
    }

    @Test
    void doCalcTest4() {
        String[][] gh = {{"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}, {"1", "1", "1", "1"}};
        assertThrows(ArraySizeException.class, ()->ArrayValueCalculator.doCalc(gh));
    }
}