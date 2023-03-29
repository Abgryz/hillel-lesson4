package ithillel.lesson15;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void sort() {
        int[] arr1 = randomArray(100_000, 100, 200);
        int[] arr2 = arr1.clone();
        QuickSort.sort(arr1, 0, arr1.length-1);
        Arrays.sort(arr2);
        assertArrayEquals(arr1, arr2);
    }


    public static int[] randomArray(int length, int min, int max) {
        int[] arr = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(max - min + 1) + min;
        }
        return arr;
    }
}
