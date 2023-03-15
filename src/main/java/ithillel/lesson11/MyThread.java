package ithillel.lesson11;

public class MyThread implements Runnable{
    private final double[] arr;

    public MyThread(double[] arr) {
        this.arr = arr;
    }

    public void run() {
        for (int i = 0; i < arr.length; i++){
            arr[i] = arr[i] * Math.sin(0.2 + i / 5.0) * Math.cos(0.2 + i / 5.0) * Math.cos(0.4 + i / 2.0);
        }
    }
}
