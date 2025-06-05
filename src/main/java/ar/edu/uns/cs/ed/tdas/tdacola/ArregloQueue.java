package ar.edu.uns.cs.ed.tdas.tdacola;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;

public class ArregloQueue <E> implements Queue<E> {
    protected E [] arreglo;
    protected int  cant,frente,cola;

    public ArregloQueue(){
        arreglo= (E[]) new Object[1000];
        cant=0;
        frente=0;
        cola=0;
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
    public E front() {
        if(isEmpty())
            throw new EmptyQueueException("No se puede hacer front de una cola vacia");
        E resultado= arreglo[frente];
        return resultado;
    }

    @Override
    public void enqueue(E element) {
       if (cant== arreglo.length){
            E[] nuevo =(E[]) new Object[arreglo.length*2]; 
            for (int i = 0; i < cant; i++) {
                nuevo[i] = arreglo[(frente + i) % arreglo.length];
            }
            arreglo= nuevo;
            frente=0;
            cola=cant;
        }
        arreglo[cola] = element;
        cola = (cola + 1) % arreglo.length;
        cant++;
    }

    @Override
    public E dequeue() {
        E resultado;
        if(isEmpty())
            throw new EmptyQueueException("No se puede eliminar de  una cola vacia");
        resultado= arreglo[frente];
        frente= (frente+1)%arreglo.length;
        cant--;
        return resultado;
    }
    
}
