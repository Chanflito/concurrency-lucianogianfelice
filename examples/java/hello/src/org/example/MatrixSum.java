package org.example;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MatrixSum {


    public static Integer sumAllLines(int[][] matrix){
        var threads=new ArrayList<Thread>();
        int numOfThreads=matrix.length;
        AtomicInteger finalResult= new AtomicInteger(0); // same problem as in rust?
        //int[] sumOfEachRow= new int[numOfThreads]; Porque aca no me deja poner un acumulador tipo entero?
        for (int i = 0; i < numOfThreads; i++) {
            int rowIndex = i;
            threads.add(new Thread( ()->{
                int acumulator=0;
                for (int j = 0; j < matrix[rowIndex].length; j++) {
                    acumulator+=matrix[rowIndex][j];
                }
                System.out.println("Sum of line "+rowIndex+" is "+acumulator);
//                sumOfEachRow[rowIndex]=acumulator;
                finalResult.addAndGet(acumulator);
            }));
        }
        for (var t : threads) {
            t.start();
        }
        //This method ensures that the principal thread  (sumAllLines) waits until all threads are finished.
        for (var t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        for (int sum : sumOfEachRow) {
//            totalSum += sum;
//        }
        return finalResult.get();
    }
}


