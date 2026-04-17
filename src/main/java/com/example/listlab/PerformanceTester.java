package com.example.listlab;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Класс для сравнения производительности встроенных коллекций Java:
 * {@link ArrayList} и {@link LinkedList}.
 *
 * Измеряет время выполнения основных операций и выводит сравнительную таблицу.
 *
 * @author Ваше Имя
 * @version 1.0
 */
public class PerformanceTester {

    private static final int NUM_OPERATIONS = 10000;
    private static final int POPULATE_SIZE = 20000;

    public static void main(String[] args) {
        System.out.println("=== Сравнение производительности ArrayList и LinkedList (встроенные) ===\n");
        System.out.printf("%-25s | %8s | %15s | %15s%n",
                "method", "count", "ArrayList (ms)", "LinkedList (ms)");
        System.out.println("-------------------------------------------------------------------");

        printResult("add end",
                testAddEnd(new ArrayList<>()),
                testAddEnd(new LinkedList<>()));

        printResult("add index (в начало)",
                testAddBegin(new ArrayList<>()),
                testAddBegin(new LinkedList<>()));

        printResult("get index (середина)",
                testGetMiddle(new ArrayList<>()),
                testGetMiddle(new LinkedList<>()));

        printResult("remove first",
                testRemoveBegin(new ArrayList<>()),
                testRemoveBegin(new LinkedList<>()));

        printResult("remove last",
                testRemoveEnd(new ArrayList<>()),
                testRemoveEnd(new LinkedList<>()));

        printResult("remove center",
                testRemoveMiddle(new ArrayList<>()),
                testRemoveMiddle(new LinkedList<>()));
    }

    /**
     * Выводит результаты замера одного метода с сравнением производительности.
     *
     * @param method       название операции
     * @param arrayTimeMs  время выполнения на ArrayList (в миллисекундах)
     * @param linkedTimeMs время выполнения на LinkedList (в миллисекундах)
     */
    private static void printResult(String method, long arrayTimeMs, long linkedTimeMs) {
        System.out.printf("%-25s | %8d | %15d | %15d | ",
                method, NUM_OPERATIONS, arrayTimeMs, linkedTimeMs);

        if (arrayTimeMs == 0 && linkedTimeMs == 0) {
            System.out.println("Too fast");
            return;
        }

        if (arrayTimeMs < linkedTimeMs) {
            double times = (double) linkedTimeMs / arrayTimeMs;
            System.out.printf("ArrayList лучше в %.1f раз%n", times);
        } else if (linkedTimeMs < arrayTimeMs) {
            double times = (double) arrayTimeMs / linkedTimeMs;
            System.out.printf("LinkedList лучше в %.1f раз%n", times);
        } else if (arrayTimeMs == linkedTimeMs) {
            System.out.println("Одинаково");
        } else {
            System.out.println("—");
        }
    }

    /**
     * Замеряет время добавления элементов в конец списка.
     *
     * @param list список для тестирования
     * @return время выполнения в миллисекундах
     */
    private static long testAddEnd(List<Integer> list) {
        long start = System.nanoTime();
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            list.add(i);
        }
        return (System.nanoTime() - start) / 1_000_000;
    }

    /**
     * Замеряет время добавления элементов в начало списка (index = 0).
     *
     * @param list список для тестирования
     * @return время выполнения в миллисекундах
     */
    private static long testAddBegin(List<Integer> list) {
        long start = System.nanoTime();
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            list.add(0, i);
        }
        return (System.nanoTime() - start) / 1_000_000;
    }

    /**
     * Замеряет время получения элементов по случайному индексу из середины списка.
     *
     * @param list список для тестирования
     * @return время выполнения в миллисекундах
     */
    private static long testGetMiddle(List<Integer> list) {
        // Предварительно заполняем список
        for (int i = 0; i < POPULATE_SIZE; i++) {
            list.add(i);
        }

        Random rand = new Random();
        long start = System.nanoTime();

        for (int i = 0; i < NUM_OPERATIONS; i++) {
            int idx = rand.nextInt(list.size());
            list.get(idx);
        }
        return (System.nanoTime() - start) / 1_000_000;
    }

    /**
     * Замеряет время удаления элементов из начала списка.
     *
     * @param list список для тестирования
     * @return время выполнения в миллисекундах
     */
    private static long testRemoveBegin(List<Integer> list) {
        for (int i = 0; i < POPULATE_SIZE; i++) list.add(i);

        long start = System.nanoTime();
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            list.remove(0);
        }
        return (System.nanoTime() - start) / 1_000_000;
    }

    /**
     * Замеряет время удаления элементов из конца списка.
     *
     * @param list список для тестирования
     * @return время выполнения в миллисекундах
     */
    private static long testRemoveEnd(List<Integer> list) {
        for (int i = 0; i < POPULATE_SIZE; i++) list.add(i);

        long start = System.nanoTime();
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            list.remove(list.size() - 1);
        }
        return (System.nanoTime() - start) / 1_000_000;
    }

    /**
     * Замеряет время удаления элементов из середины списка.
     *
     * @param list список для тестирования
     * @return время выполнения в миллисекундах
     */
    private static long testRemoveMiddle(List<Integer> list) {
        for (int i = 0; i < POPULATE_SIZE; i++) list.add(i);

        long start = System.nanoTime();
        for (int i = 0; i < NUM_OPERATIONS; i++) {
            list.remove(list.size() / 2);
        }
        return (System.nanoTime() - start) / 1_000_000;
    }
}

/*
 * package com.example.listlab;
 * 
 * import java.util.Random;
 * 
 *//**
	 * Класс для проведения тестов производительности двух реализаций списка:
	 * {@link MyArrayList} и {@link MyLinkedList}.
	 * 
	 * Измеряет время выполнения основных операций и выводит сравнительную таблицу.
	 */
/*
 * public class PerformanceTester {
 *//**
	 * Количество операций.
	 */
/*
 * private static final int NUM_OPERATIONS = 10000;
 *//**
	 * Размер.
	 */
/*
 * private static final int POPULATE_SIZE = 20000;
 * 
 * public static void main(String[] args) {
 * System.out.println("effective MyArrayList with MyLinkedList\n");
 * System.out.printf("%-15s | %8s | %15s | %15s%n", "method", "count",
 * "MyArrayList (ms)", "MyLinkedList (ms)"); System.out.println(
 * "-------------------------------------------------------------------");
 * 
 * printResult("add end", testAddEnd(new MyArrayList<>()), testAddEnd(new
 * MyLinkedList<>()));
 * 
 * printResult("add index", testAddBegin(new MyArrayList<>()), testAddBegin(new
 * MyLinkedList<>()));
 * 
 * printResult("get index", testGetMiddle(new MyArrayList<>()),
 * testGetMiddle(new MyLinkedList<>()));
 * 
 * printResult("remove first", testRemoveBegin(new MyArrayList<>()),
 * testRemoveBegin(new MyLinkedList<>()));
 * 
 * printResult("remove last", testRemoveEnd(new MyArrayList<>()),
 * testRemoveEnd(new MyLinkedList<>()));
 * 
 * printResult("remove center", testRemoveMiddle(new MyArrayList<>()),
 * testRemoveMiddle(new MyLinkedList<>()));
 * 
 * }
 *//**
	 * Выводит результаты замера одного метода с сравнением производительности.
	 *
	 * @param method       название операции
	 * @param arrayTimeMs  время выполнения на MyArrayList (в миллисекундах)
	 * @param linkedTimeMs время выполнения на MyLinkedList (в миллисекундах)
	 */
/*
 * private static void printResult(String method, long arrayTimeMs, long
 * linkedTimeMs) { System.out.printf("%-28s | %8d | %12d | %12d | ", method,
 * NUM_OPERATIONS, arrayTimeMs, linkedTimeMs);
 * 
 * if (arrayTimeMs == 0 && linkedTimeMs == 0) {
 * 
 * System.out.println("Too fast"); return; }
 * 
 * if (arrayTimeMs < linkedTimeMs) { double times = (double) linkedTimeMs /
 * arrayTimeMs; System.out.printf("ArrayList better %.1f%n", times); } else if
 * (linkedTimeMs < arrayTimeMs) { double times = (double) arrayTimeMs /
 * linkedTimeMs; System.out.printf("LinkedList better %.1f%n", times); } else if
 * (arrayTimeMs == linkedTimeMs) { System.out.println("Identically"); } else {
 * System.out.println("—"); } }
 *//**
	 * Замеряет время добавления элементов в конец списка.
	 * 
	 * @param list список, на котором тестируем
	 */
/*
 * private static long testAddEnd(MyList<Integer> list) { long start =
 * System.nanoTime(); for (int i = 0; i < NUM_OPERATIONS; i++) list.add(i);
 * return (System.nanoTime() - start) / 1_000_000; }
 *//**
	 * Замеряет время добавления элементов в начало списка.
	 * 
	 * @param list список, на котором тестируем
	 */
/*
 * private static long testAddBegin(MyList<Integer> list) { long start =
 * System.nanoTime(); for (int i = 0; i < NUM_OPERATIONS; i++) list.add(0, i);
 * return (System.nanoTime() - start) / 1_000_000; }
 *//**
	 * Замеряет время получения элементов по случайному индексу из середины списка.
	 * 
	 * @param list список, на котором тестируем
	 */
/*
 * private static long testGetMiddle(MyList<Integer> list) { for (int i = 0; i <
 * POPULATE_SIZE; i++) list.add(i); Random rand = new Random(); long start =
 * System.nanoTime(); for (int i = 0; i < NUM_OPERATIONS; i++) { int idx =
 * rand.nextInt(list.size()); list.get(idx); } return (System.nanoTime() -
 * start) / 1_000_000; }
 *//**
	 * Замеряет время удаления элементов из начала списка.
	 * 
	 * @param list список, на котором тестируем
	 */
/*
 * private static long testRemoveBegin(MyList<Integer> list) { for (int i = 0; i
 * < POPULATE_SIZE; i++) list.add(i);
 * 
 * long start = System.nanoTime(); for (int i = 0; i < NUM_OPERATIONS; i++)
 * list.remove(0); return (System.nanoTime() - start) / 1_000_000; }
 *//**
	 * Замеряет время удаления элементов из конца списка.
	 * 
	 * @param list список, на котором тестируем
	 */
/*
 * private static long testRemoveEnd(MyList<Integer> list) { for (int i = 0; i <
 * POPULATE_SIZE; i++) list.add(i); long start = System.nanoTime(); for (int i =
 * 0; i < NUM_OPERATIONS; i++) list.remove(list.size() - 1); return
 * (System.nanoTime() - start) / 1_000_000; }
 *//**
	 * Замеряет время удаления элементов из середины списка.
	 * 
	 * @param list список, на котором тестируем
	 *//*
		 * private static long testRemoveMiddle(MyList<Integer> list) { for (int i = 0;
		 * i < POPULATE_SIZE; i++) list.add(i); long start = System.nanoTime(); for (int
		 * i = 0; i < NUM_OPERATIONS; i++) list.remove(list.size() / 2); return
		 * (System.nanoTime() - start) / 1_000_000; } }
		 */