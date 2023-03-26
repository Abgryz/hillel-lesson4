package ithillel.lesson13;

import java.util.concurrent.RecursiveTask;

public class ArrayTask extends RecursiveTask<double[]> {
    private final double[] data;
    private final int start;
    private final int end;

    public ArrayTask(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected double[] compute() {
        if(end - start < 50){
            System.out.println("Starting... " + Thread.currentThread().getName());
            for(int i = start; i <= end; i++){
                data[i] = data[i] * Math.sin(0.2 + i / 5.0) * Math.cos(0.2 + i / 5.0) * Math.cos(0.4 + i / 2.0);
            }
        }
        else {
            int middle = (start + end)/2;

            ArrayTask left = new ArrayTask(data, start, middle);
            ArrayTask right = new ArrayTask(data, middle+1, end);

            left.fork();
            right.fork();

            double[] leftResult = left.join();
            double[] rightResult = right.join();

            System.arraycopy(data, start, leftResult, start, middle - start);
            System.arraycopy(data, middle+1, rightResult, middle+1, end - middle);
        }
        return data;
    }
}
