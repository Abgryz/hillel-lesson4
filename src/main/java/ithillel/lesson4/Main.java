package ithillel.lesson4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] input = new String[4][4];
        Scanner sc = new Scanner(System.in);
        System.out.println("Input array (4x4):");
        for(int i = 0;i < 4;i++){
            for(int j = 0; j < 4; j++){
                input[i][j] = sc.next();
            }
        }
        try{
            System.out.println(ArrayValueCalculator.doCalc(input));
        } catch (ArraySizeException e){
            e.printStackTrace();
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        finally {
            System.out.println("done");
        }
    }
}