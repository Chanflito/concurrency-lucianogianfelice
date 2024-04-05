package org.example;

import java.util.ArrayList;

public class HelloThreads {
    private static Thread hello() throws InterruptedException {
        var t1 = new Thread(() ->{
            sleep(1000);
            //printHello();
        } );
        var t2 = new Thread(() -> System.out.println("Hello from thread 2"));
        t1.start();
        t2.start();
        //t1.join();
        t2.join();// T2 se hace join al hilo principal.
        return t1;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("====== Hello Threads    =======");
//        hello();
        printHello20();
        System.out.println("====== Good Bye Threads =======");
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
    private static void printHello20() throws InterruptedException {
        var threads=new ArrayList<Thread>();
        for (int i = 0; i < 20; i++) {
            //Create a thread
            threads.add(new Thread(() -> System.out.println("Hello from thread " + Thread.currentThread().getId())));
        }

        for (var t : threads) {
            t.start();
        }
        for (var t : threads) {
            t.join();
        }
    }
}