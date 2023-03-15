package ithillel.lesson11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayInitializerTest {

    private final double[] ARR_EVEN = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

    private final double[] ARR_ODD = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

    @Test
    void initTestEven() {
        double[] arrCopy = ARR_EVEN.clone();
        int arrLength = arrCopy.length;
        for (int i = 0; i < arrLength/2; i++){
            arrCopy[i] = arrCopy[i] * Math.sin(0.2 + i / 5.0) * Math.cos(0.2 + i / 5.0) * Math.cos(0.4 + i / 2.0);
            arrCopy[arrLength/2 + i] = arrCopy[i];
        }
        ArrayInitializer.init(ARR_EVEN);
        assertArrayEquals(ARR_EVEN, arrCopy);
    }

    @Test
    void initTestOdd() {
        double[] arrCopy = ARR_ODD.clone();
        int arrLength = arrCopy.length;
        for (int i = 0; i < arrLength/2; i++){
            arrCopy[i] = arrCopy[i] * Math.sin(0.2 + i / 5.0) * Math.cos(0.2 + i / 5.0) * Math.cos(0.4 + i / 2.0);
            arrCopy[arrLength/2 + i] = arrCopy[i];
        }
        int index = arrLength - arrLength/2 - 1;
        arrCopy[arrLength - 1] = arrCopy[arrLength - 1] * Math.sin(0.2 + index / 5.0) * Math.cos(0.2 + index / 5.0) * Math.cos(0.4 + index / 2.0);

        ArrayInitializer.init(ARR_ODD);
        assertArrayEquals(ARR_ODD, arrCopy);
    }

}