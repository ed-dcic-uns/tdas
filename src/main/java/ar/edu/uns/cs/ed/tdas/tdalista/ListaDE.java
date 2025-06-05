package ar.edu.uns.cs.ed.tdas.tdalista;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;

public class ListaDE<E> implements PositionList<E> {
    protected int cant;
    protected DNode<E> primero,ultimo;

    public ListaDE(){
        cant=0;
        primero= new DNode(null);
        ultimo= new DNode(null);
        primero.setSiguiente(ultimo);
        ultimo.setAnterior(primero);
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
    public Position<E> first() {
        if (isEmpty())
            throw new EmptyListException("Se intento hacer first en una lista vacia");
        return primero.getSiguiente();
    }

    @Override
    public Position<E> last() {
        if(isEmpty()){
            throw new EmptyListException("Se intento hacer last a una lista vacia"); 
        }
        return ultimo.getAnterior();
    }

    @Override
    public Position<E> next(Position<E> p) {
        DNode<E> nodo= checkPosition(p);
        if(nodo.getSiguiente()== ultimo)
            throw new BoundaryViolationException("No hay siguiente para la última posición");
        return nodo.getSiguiente();
    }

    @Override
    public Position<E> prev(Position<E> p) {
        DNode<E> nodo= checkPosition(p);
        if(nodo.getAnterior()==primero)
            throw new BoundaryViolationException("No hay anterior para la primera posición");
        return nodo.getAnterior();
    }

    @Override
    public void addFirst(E element) {
        agregarEnElMedio(primero,primero.getSiguiente(), element);
    }

    @Override
    public void addLast(E element) {
        agregarEnElMedio(ultimo.getAnterior(),ultimo, element);
    }

    @Override
    public void addAfter(Position<E> p, E element) {
        if (isEmpty())
            throw new InvalidPositionException(null);
        DNode<E> anterior=checkPosition(p);
        agregarEnElMedio(anterior,anterior.getSiguiente(), element);
    }

    @Override
    public void addBefore(Position<E> p, E element) {
        if(this.isEmpty()) {
            throw new InvalidPositionException(null);
        }
		DNode<E> siguiente = checkPosition(p);
		agregarEnElMedio(siguiente.getAnterior(), siguiente, element);
    }

    @Override
    public E remove(Position<E> p) {
        if (isEmpty())
            throw new EmptyListException("Se intento remove en una lista vacia");
        DNode<E> nodo = checkPosition(p);
        nodo.getAnterior().setSiguiente(nodo.getSiguiente());
        nodo.getSiguiente().setAnterior(nodo.getAnterior());
        cant--;
        return nodo.getElemento();
    }

    @Override
    public E set(Position<E> p, E element) {
        DNode<E> nodo = checkPosition(p);
        E viejo= nodo.element();
        nodo.setElemento(element);
        return viejo;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            DNode<E> actual = primero.getSiguiente();

            public boolean hasNext() {
                return actual != ultimo;
            }

            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                    E elem = actual.element();
                    actual = actual.getSiguiente();
                return elem;
            }
        };
    }
    @Override
    public Iterable<Position<E>> positions() {
        List<Position<E>> snapshot = new ArrayList<>();
        DNode<E> current =  primero.getSiguiente();
        while (current != ultimo) {
            snapshot.add(current);
            current = current.getSiguiente();
        }
    return snapshot;
}

    protected void agregarEnElMedio(DNode<E> anterior,DNode<E> siguiente, E elem){
        DNode<E> nodo= new DNode(elem);
        nodo.setSiguiente(siguiente);
        nodo.getSiguiente().setAnterior(nodo);
        anterior.setSiguiente(nodo);
        nodo.setAnterior(anterior);
        cant++;
    }
    private DNode<E> checkPosition(Position<E> p) throws InvalidPositionException{
        DNode<E> n;
        if(p==null){
            throw new InvalidPositionException("Posicion Nula. Posicion Invalida");
        }
        if(isEmpty()){
            throw new EmptyListException("No se puede operar sobre una lista vacia ");
        }
        try {
            n=(DNode<E>)p;
        } catch (ClassCastException e) {
            throw new InvalidPositionException("Posicion invalida");
        }
        return n;
    }
    public void agregarSegundoyAnteultimo(E e1,E e2){
        if(cant==0){
            addFirst(e1);
            addLast(e2);
        }
        else if(cant==1){
            addLast(e1);
            addLast(e2);
        }
        else{
            agregarEnElMedio(primero.getSiguiente(), primero.getSiguiente().getSiguiente(), e1);
            agregarEnElMedio(ultimo.getAnterior(),ultimo.getAnterior().getAnterior(), e2);

        }
    }    
    
}
