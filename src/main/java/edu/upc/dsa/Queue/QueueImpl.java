package edu.upc.dsa.Queue;

public class QueueImpl<E> implements QueueInterface<E>{
    private E[] data;
    private int size;

    private int front;
    private int rear;

    /*La anotación @SuppressWarnings("unchecked") se utiliza
    para indicar al compilador de Java que ignore las advertencias relacionadas
    con la conversión sin comprobación de tipos genéricos. En este contexto específico,
    estás utilizando la conversión de un Object[] a un arreglo de tipo E[]. */
    @SuppressWarnings("unchecked")
    public QueueImpl(int capacity){
        data = (E[]) new Object[capacity];
        size = 0;
        front = rear = 0;
    }

    @Override
    public void push(E e) throws FullQueueException {
        if(size >= data.length){
            throw new FullQueueException("La cola está llena");
        }
        if(size == 0){
            front = rear = 0;
        }
        else{
            rear = (rear + 1)% data.length;
        }
        data[rear] = e;
        size++;

    }

    @Override
    public E pop() throws EmptyQueueException {
        if(size == 0){
            throw new EmptyQueueException("La cola está vacía");
        }
        E element = data[front];
        if(front == rear){
            front = rear = -1;
        }
        else{
            front = (front + 1) % data.length;
        }
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }
}