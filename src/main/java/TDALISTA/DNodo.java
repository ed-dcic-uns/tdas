package TDALISTA;

import ar.edu.uns.cs.ed.tdas.Position;
public class DNodo<E> implements Position<E> {

    // --- Estado ----
    private E elemento;           // rótulo
    private DNodo<E> prev;        // nodo anterior
    private DNodo<E> next;        // nodo siguiente

    // --- Constructores ----
    /** Crea un nodo aislado cuyo prev y next valen null */
    public DNodo(E elem) {
        this(elem, null, null);
    }

    /** Crea un nodo con enlaces explícitos */
    public DNodo(E elem, DNodo<E> prev, DNodo<E> next) {
        this.elemento = elem;
        this.prev      = prev;
        this.next      = next;
    }

    // --- Getters ----
    @Override                      // de Position<E>
    public E element() {
        return elemento;
    }

    public DNodo<E> getPrev() {
        return prev;
    }

    public DNodo<E> getSiguente() {
        return next;
    }

    // --- Setters ----
    public void setSiguente(DNodo<E> cola) {
        this.elemento = cola;
    }

    public void setSiguiente(DNodo<E> cabeza) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSiguiente'");
    }

    public Position<E> getAnterior() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAnterior'");
    }

    public DNodo<E> getSiguiente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSiguiente'");
    }
}
