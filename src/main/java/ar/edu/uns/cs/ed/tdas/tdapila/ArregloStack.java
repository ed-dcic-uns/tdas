package ar.edu.uns.cs.ed.tdas.tdapila;
import  ar.edu.uns.cs.ed.tdas.excepciones.*;

public class ArregloStack<E> implements Stack<E> {
    protected E [] arreglo;
    protected int cant;

    public ArregloStack(){
        arreglo= (E[])new Object[100];
        cant=0;
    }

    @Override
    public int size() {
       return cant;
    }

    @Override
    public boolean isEmpty() {
        return cant==0;
    }

    @Override
    public E top() {
        if(isEmpty())
            throw new EmptyStackException("No se puede hacer top a una pila vacia");
        E resultado= arreglo[cant-1];
        return resultado;
    }

    @Override
    public void push(E element) {
        if(cant==arreglo.length){
        E[] nuevo=  (E[])new Object[arreglo.length*2];
            for(int i=0;i<arreglo.length;i++){
                nuevo[i]=arreglo[i];
            }
            arreglo=nuevo;
        }
        arreglo[cant]=element;
        cant++;
    }

    @Override
    public E pop() {
       if (isEmpty())
        throw new EmptyStackException("No se puede hacer pop de una pila vacia");
       E resu= arreglo[cant-1];
       cant--;
       return resu; 
    }
    
}
