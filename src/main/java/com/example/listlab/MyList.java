package com.example.listlab;

public interface MyList<E>
{
    void add(E element);
    void add(int index, E element);
    E get(int index);
    E remove(int index);
    int size();
}