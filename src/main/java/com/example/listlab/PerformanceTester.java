package com.example.listlab;

import java.util.Random;

public class PerformanceTester 
{
    private static final int NUM_OPERATIONS = 10000;  
    private static final int POPULATE_SIZE = 20000;

    public static void main(String[] args) 
    {
        System.out.println("effective MyArrayList with MyLinkedList\n");
        System.out.printf("%-15s | %8s | %15s | %15s%n", 
                "method", "count", "MyArrayList (ms)", "MyLinkedList (ms)");
        System.out.println("-------------------------------------------------------------------");

        printResult("add end", 
                testAddEnd(new MyArrayList<>()), 
                testAddEnd(new MyLinkedList<>()));

        printResult("add index", 
                testAddBegin(new MyArrayList<>()), 
                testAddBegin(new MyLinkedList<>()));

        printResult("get index", 
                testGetMiddle(new MyArrayList<>()), 
                testGetMiddle(new MyLinkedList<>()));

        printResult("remove first", 
                testRemoveBegin(new MyArrayList<>()), 
                testRemoveBegin(new MyLinkedList<>()));

        printResult("remove last", 
                testRemoveEnd(new MyArrayList<>()), 
                testRemoveEnd(new MyLinkedList<>()));

        printResult("remove center", 
                testRemoveMiddle(new MyArrayList<>()), 
                testRemoveMiddle(new MyLinkedList<>()));

    }

    private static void printResult(String method, long arrayTimeMs, long linkedTimeMs) 
    {
        System.out.printf("%-28s | %8d | %12d | %12d | ", 
                method, NUM_OPERATIONS, arrayTimeMs, linkedTimeMs);

        if (arrayTimeMs == 0 && linkedTimeMs == 0) 
        {
        	
            System.out.println("Too fast");
            return;
        }

        if (arrayTimeMs < linkedTimeMs) 
        {
            double times = (double) linkedTimeMs / arrayTimeMs;
            System.out.printf("ArrayList better %.1f%n", times);
        } 
        else if (linkedTimeMs < arrayTimeMs) 
        {
            double times = (double) arrayTimeMs / linkedTimeMs;
            System.out.printf("LinkedList better %.1f%n", times);
        } 
        else if (arrayTimeMs == linkedTimeMs) 
        {
            System.out.println("Identically");
        } 
        else 
        {
            System.out.println("—");
        }
    }

    private static long testAddEnd(MyList<Integer> list) 
    {
        long start = System.nanoTime();
        for (int i = 0; i < NUM_OPERATIONS; i++) list.add(i);
        return (System.nanoTime() - start) / 1_000_000;
    }

    private static long testAddBegin(MyList<Integer> list) 
    {
        long start = System.nanoTime();
        for (int i = 0; i < NUM_OPERATIONS; i++) list.add(0, i);
        return (System.nanoTime() - start) / 1_000_000;
    }

    private static long testGetMiddle(MyList<Integer> list) 
    {
        for (int i = 0; i < POPULATE_SIZE; i++) list.add(i);
        Random rand = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < NUM_OPERATIONS; i++)
        {
            int idx = rand.nextInt(list.size());
            list.get(idx);
        }
        return (System.nanoTime() - start) / 1_000_000;
    }

    private static long testRemoveBegin(MyList<Integer> list) 
    {
        for (int i = 0; i < POPULATE_SIZE; i++) list.add(i);
        
        long start = System.nanoTime();
        for (int i = 0; i < NUM_OPERATIONS; i++) list.remove(0);
        return (System.nanoTime() - start) / 1_000_000;
    }

    private static long testRemoveEnd(MyList<Integer> list) 
    {
        for (int i = 0; i < POPULATE_SIZE; i++) list.add(i);
        long start = System.nanoTime();
        for (int i = 0; i < NUM_OPERATIONS; i++) list.remove(list.size() - 1);
        return (System.nanoTime() - start) / 1_000_000;
    }

    private static long testRemoveMiddle(MyList<Integer> list) 
    {
        for (int i = 0; i < POPULATE_SIZE; i++) list.add(i);
        long start = System.nanoTime();
        for (int i = 0; i < NUM_OPERATIONS; i++) list.remove(list.size() / 2);
        return (System.nanoTime() - start) / 1_000_000;
    }
}