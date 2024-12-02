package org.example.counter;

public class Counter implements Runnable {

    private int count = 0;
    public void increment() {
        count++;
    }
    public void decrement() {
        count--;
    }

    public int getValue() {
        return count;
    }
        //1. 동기화 전
//    @Override
//    public void run() {
//        this.increment();
//        System.out.println("value for Thread After increment "+Thread.currentThread().getName()+" :"+getValue());
//        // 기대값 1
//        this.decrement();
//        System.out.println("value for Thread at last "+Thread.currentThread().getName()+" :"+getValue());
//        // 기대값 0
//    }

    // 2. 동기화 후
    @Override
    public void run() {
        synchronized (this) {
            this.increment();
            System.out.println("value for Thread After increment "+Thread.currentThread().getName()+" :"+getValue());
            // 기대값 1
            this.decrement();
            System.out.println("value for Thread at last "+Thread.currentThread().getName()+" :"+getValue());
            // 기대값 0
        }
    }
}
