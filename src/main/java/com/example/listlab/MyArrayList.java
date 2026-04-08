package com.example.listlab;

/**
 * Реализация динамического массива (ArrayList).
 * Основана на обычном массиве с автоматическим увеличением размера(ёмкости).
 *
 * @param <E> тип элементов в списке
 */
public class MyArrayList<E> implements MyList<E> 
{
	/**
	 *  Дефолтный размер
	 */
    private static final int DEFAULT_CAPACITY = 10;
	/**
	 *  Массив, куда будем все записывать
	 */
    private Object[] elementData;
	/**
	 *  Размер
	 */
    private int size;
    /**
     * Создаёт пустой список с начальным размером по умолчанию (10).
     */
    public MyArrayList() 
    {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Создаёт пустой список с указанным начальным размером.
     *
     * @param initialCapacity начальный размер массива
     * @throws IllegalArgumentException если initialCapacity < 0
     */
    public MyArrayList(int initialCapacity) 
    {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity < 0 ERROR! " + initialCapacity);
        }
        elementData = new Object[initialCapacity];
        size = 0;
    }
    /**
     * Создаёт список с указанным начальным размером и первым элементом.
     *
     * @param initialCapacity начальный размер
     * @param firstElement    первый элемент списка
     */
    public MyArrayList(int initialCapacity, E firstElement) 
    {
        this(initialCapacity);    
        add(firstElement);          
    }

    @Override
    public void add(E element) 
    {
        if (size == elementData.length) 
        {
            ensureCapacity();
        }
        elementData[size++] = element;
    }
    /**
     * Увеличивает ёмкость внутреннего массива в 2 раза.
     */
    private void ensureCapacity() 
    {
        int newCapacity = elementData.length * 2;
        Object[] newData = new Object[newCapacity];
        System.arraycopy(elementData, 0, newData, 0, size);
        elementData = newData;
    }

    @Override
    public void add(int index, E element) 
    {
        if (index < 0 || index > size) 
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (size == elementData.length) 
        {
            ensureCapacity();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }


    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) 
    {
        if (index < 0 || index >= size) 
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elementData[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) 
    {
        if (index < 0 || index >= size) 
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E oldValue = (E) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return oldValue;
    }

    @Override
    public int size() 
    {
        return size;
    }
}