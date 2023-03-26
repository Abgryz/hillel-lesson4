package ithillel.lesson13;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTaskTest {
    @Test
    public void arrayTaskTest(){
        double[] forkJoinData = new double[10000], data = new double[10000];
        Arrays.fill(forkJoinData, 10);
        Arrays.fill(data, 10);
        for(int i = 0; i < data.length; i++){
            data[i] = data[i] * Math.sin(0.2 + i / 5.0) * Math.cos(0.2 + i / 5.0) * Math.cos(0.4 + i / 2.0);
        }

        ForkJoinPool pool = new ForkJoinPool();
        forkJoinData = pool.invoke(new ArrayTask(forkJoinData, 0, forkJoinData.length - 1));
        assertArrayEquals(forkJoinData, data);
    }
}