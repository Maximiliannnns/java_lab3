package com.example.listlab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest 
{

    private MyList<Integer> list;

    @BeforeEach
    void setUp() 
    {
        list = new MyLinkedList<>();          
    }

    @Test
    void testAdd() 
    {
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(3, list.size());
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void testAddAtIndex()
    {
        list.add(100);
        list.add(200);
        list.add(1, 999);
        assertEquals(3, list.size());
        assertEquals(100, list.get(0));
        assertEquals(999, list.get(1));
        assertEquals(200, list.get(2));
    }

    @Test
    void testConstructorWithFirstElement() 
    {
        MyList<Integer> list2 = new MyLinkedList<>(777);
        assertEquals(1, list2.size());
        assertEquals(777, list2.get(0));
    }

    @Test
    void testGet()
    {
        list.add(5);
        list.add(6);
        assertEquals(5, list.get(0));
        assertEquals(6, list.get(1));
    }

    @Test
    void testRemove()
    {
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(20, list.remove(1));
        assertEquals(2, list.size());
        assertEquals(10, list.get(0));
        assertEquals(30, list.get(1));
    }

    @Test
    void testRemoveFirstAndLast()
    {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(1, list.remove(0));
        assertEquals(3, list.remove(1));
        assertEquals(1, list.size());
    }

    @Test
    void testSizeEmptyList()
    {
        assertEquals(0, list.size());
    }

    @Test
    void testIndexOutOfBounds() 
    {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 5));
    }

    @Test
    void testAddManyElements()
    {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        assertEquals(100, list.size());
        assertEquals(50, list.get(50));
    }
}