package com.zyh.javatraining.weekfour;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

public class NewThread {
    public static void main(String[] args) {
        method1();
        method2();
        method3();
        method4();
        method5();
    }

    public volatile static int answer1 = 0;
    public static void method1() {
        Thread thread = new Thread(()->{
            answer1 = 1;
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(answer1);
    }

    public static void method2() {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            return 1;
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void method3() {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future future = service.submit(() -> {
            return 1;
        });
        try {
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdownNow();
    }

    public static volatile int answer4 = 0;
    public static void method4() {
        Object object = new Object();
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            answer4 = 1;
            synchronized (object) {
                object.notifyAll();
            }
        });
        thread.start();
        try {
            synchronized (object) {
                object.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(answer4);
    }

    public static void method5() {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.add(1);
        });
        thread.start();
        try {
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
