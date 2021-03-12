package com.example.demo.training;

public class MyRunnable implements Runnable {

    private static int val = 0;

    @Override
    public void run() {
        System.out.println("Doing heavy processing - START " + Thread.currentThread().getName());
        System.out.println("getVal: " + getVal());
        try {
            Thread.sleep(1);
            System.out.println("incVal: " + incVal());
            //System.out.println("Doing heavy processing - END " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public int getVal() {
        return val;
    }

    public int incVal() {
        return ++val;
    }
}