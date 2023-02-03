package ithillel.lesson4;

import java.lang.reflect.Array;

public class ArrayValueCalculator {
    public static int doCalc(String[][] arr){
        int sum = 0, i = 0, j = 0;
        if(arr.length != 4){
            throw new ArraySizeException("Illegal array size!");
        }
        try{
            for (i = 0; i < 4; i++) {
                if(arr[i].length != 4){
                    throw new ArraySizeException("Illegal array size!");
                }
                for (j = 0; j < 4; j++){
                    sum += Integer.parseInt(arr[i][j]);
                }
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(i + "-th and " + j + "-th element has invalid format");
        }

        return sum;
    }
}
