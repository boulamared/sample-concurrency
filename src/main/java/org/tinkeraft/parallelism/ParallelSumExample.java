package org.tinkeraft.parallelism;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSumExample extends RecursiveTask<Long> {

    // Threshold for parallelism
    private static final int THRESHOLD = 1000;
    private final int[] array;
    private final int start;
    private final int end;

    public ParallelSumExample(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            // If the array size is below the threshold, perform sequential sum
            return sequentialSum();
        }
        // Split the task into subtasks
        int mid = start + length / 2;
        ParallelSumExample leftTask = new ParallelSumExample(array, start, mid);
        ParallelSumExample rightTask = new ParallelSumExample(array, mid, end);
        // Fork the subtasks
        leftTask.fork();
        Long rightResult = rightTask.compute();
        // Join the results
        Long leftResult = leftTask.join();
        // Combine the results
        return leftResult + rightResult;
    }

    private long sequentialSum() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
        return sum;
    }


    public static void main(String[] args) {
        int[] array = new int[10000]; // Initialize the array with values
        // Create ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // Create a task for the entire array
        ParallelSumExample task = new ParallelSumExample(array, 0, array.length);
        // Invoke the task using ForkJoinPool
        long result = forkJoinPool.invoke(task);
        System.out.println("Sum: " + result);
    }
}
