package ithillel.lesson11;

public class ArrayInitializer {
    public static void init(double[] arr){
        assert arr.length != 0;

        double[] arrThread1 = new double[arr.length/2];
        double[] arrThread2 = new double[arr.length/2 + 1];
        boolean isEven = arr.length % 2 == 0;

        System.arraycopy(arr, 0, arrThread1, 0, arr.length/2);
        System.arraycopy(arr, arr.length/2, arrThread2, 0, arr.length/2 + (isEven ? 0:1));

        Thread thr1 = new Thread(new MyThread(arrThread1));
        Thread thr2 = new Thread(new MyThread(arrThread2));

        thr1.start();
        thr2.start();
        try {
            thr1.join();
            thr2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.arraycopy(arrThread1, 0, arr, 0, arrThread1.length);
        System.arraycopy(arrThread2, 0, arr, arrThread1.length, arrThread2.length - (isEven ? 1:0));
    }
}
