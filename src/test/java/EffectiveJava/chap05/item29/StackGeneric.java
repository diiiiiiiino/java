package EffectiveJava.chap05.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

public class StackGeneric<E> {
    private E[] elements;
    //private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public StackGeneric(){
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        //elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e){
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop(){
        if (size == 0)
            throw new EmptyStackException();

        /*@SuppressWarnings("unchecked")
        E result = (E) elements[--size];*/
        E result = elements[--size];
        elements[size] = null;
        return result;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void ensureCapacity(){
        if(elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
