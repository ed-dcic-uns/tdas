package ar.edu.uns.cs.ed.tdas.tdacola;
import java.util.Stack;

import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;
public class ColaconPila<E> implements Queue<E> {
    
    protected Stack<E> pila;

    public ColaconPila(){
        pila= new Stack<>();
    }

    @Override
    public int size() {
        return pila.size();
    }

    @Override
    public boolean isEmpty() {
        return pila.isEmpty();
    }

    @Override
    public E front() {
        if(pila.isEmpty())
            throw new EmptyQueueException("La cola esta Vacia");
        Stack<E> aux= new Stack<>();
        while(!pila.isEmpty()){
            aux.push(pila.pop());
        }
        E resultado= aux.peek();
        while(!aux.isEmpty()){
            pila.push(aux.pop());
        }
        return resultado;
    }

    @Override
    public void enqueue(E element) {
        pila.push(element);
    }

    @Override
    public E dequeue() {
        if(pila.isEmpty())
            throw new EmptyQueueException("No se puede eliminar de una cola vacia");
        E resultado= pila.pop();
        return resultado;
    }
    
}
