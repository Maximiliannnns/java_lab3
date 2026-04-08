package com.example.listlab;

public class MyLinkedList<E> implements MyList<E> 
{

    private static class Node<E> 
    {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(E item) 
        {
            this.item = item;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyLinkedList() 
    {
        head = tail = null;
        size = 0;
    }

    public MyLinkedList(E firstElement)
    {
        this();                    
        add(firstElement); 
    }

    @Override
    public void add(E element)
    {
        addLast(element);
    }

    private void addLast(E element) 
    {
        Node<E> newNode = new Node<>(element);
        if (tail == null) 
        {
            head = tail = newNode;
        } else 
        {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, E element) 
    {
        if (index < 0 || index > size) 
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) 
        {
            addFirst(element);
            return;
        }
        if (index == size) 
        {
            addLast(element);
            return;
        }

        Node<E> current = getNode(index);
        Node<E> newNode = new Node<>(element);
        newNode.prev = current.prev;
        newNode.next = current;
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
    }

    private void addFirst(E element) 
    {
        Node<E> newNode = new Node<>(element);
        if (head == null) 
        {
            head = tail = newNode;
        } else
        {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) 
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return getNode(index).item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) 
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> nodeToRemove = getNode(index);
        E item = nodeToRemove.item;

        if (nodeToRemove.prev != null)
        {
            nodeToRemove.prev.next = nodeToRemove.next;
        } else {
            head = nodeToRemove.next;
        }
        if (nodeToRemove.next != null)
        {
            nodeToRemove.next.prev = nodeToRemove.prev;
        } else {
            tail = nodeToRemove.prev;
        }

        size--;
        return item;
    }

    
    private Node<E> getNode(int index) 
    {
        Node<E> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) 
            {
                current = current.next;
            }
        } else 
        {
            current = tail;
            for (int i = size - 1; i > index; i--) 
            {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public int size() 
    {
        return size;
    }
}