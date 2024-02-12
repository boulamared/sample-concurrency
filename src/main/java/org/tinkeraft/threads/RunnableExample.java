package org.tinkeraft.threads;

public class RunnableExample {
    public static void main(String[] args) {
        Thread runnableThread = new Thread(new MyRunnable());
        runnableThread.start();
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