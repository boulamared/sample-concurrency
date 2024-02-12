package org.tinkeraft.threads;

public class MyThreadExample {
    public static void main(String[] args) {
        MyThread threadClassThread = new MyThread();
        threadClassThread.start();
        for (int i = 1; i <= 5; i++) {
            System.out.println("Main Thread: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}