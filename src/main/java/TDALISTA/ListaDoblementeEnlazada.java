package TDALISTA;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;


public class ListaDoblementeEnlazada<E> implements PositionList<E>{
	private DNodo<E> cabeza;
    private DNodo<E> cola;
    private int size;
	
	public ListaDoblementeEnlazada() {
		cabeza = new DNodo<E>(null); // centinela inicial
        cola = new DNodo<E>(null); // centinela final
        cabeza.setSiguente(cola);
        cola.setSiguienteS(cabeza);
        size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public Position<E> first() {
		if(cabeza.element() == null)
			throw new EmptyListException("No hay nada en la primera pocision");
	
		return  cabeza.getSiguente();
	}

	@Override
	public Position<E> last() {
		if(cola.element() == null)
			throw new EmptyListException("No hay nada en la primera pocision");
	
		return  cola.getAnterior();
	}

	@Override
	public Position<E> next(Position<E> p) {
		DNodo<E> NuevoNodo = checkPosition(p);
		
		if(NuevoNodo.getSiguiente() == cola) 
			throw new InvalidPositionException("Lista vacia no se puede agregar nada");
		
		return cabeza.getSiguiente();
	}

	@Override
	public Position<E> prev(Position<E> p) {
		DNodo<E> nodo = checkPosition(p);
		 
		if(cola.element() == null)
			throw new InvalidPositionException("Lista vacia no se puede realizar la siguiente operacion");
		
		if(cola.element() == p)
			throw new BoundaryViolationException("No exite elemento previo al primero");
		
		return nodo.getSiguiente();
	}

	@Override
	public void addFirst(E element) {
		 agregarEnElMedio(cola, cola.getSiguiente(), element);
	}

	@Override
	public void addLast(E element) {
		agregarEnElMedio(cabeza.getAnterior(), cabeza, element);
		
	}

	@Override
	public void addAfter(Position<E> p, E element) {
		if (isEmpty())
			throw new InvalidPositionException(null);
		
		DNodo<E> nodo = checkPosition(p);
		
		agregarEnElMedio(nodo, nodo.getSiguiente(), element);
		
	}

	@Override
	public void addBefore(Position<E> p, E element) {
		DNodo<E> nodo = checkPosition(p); 
		
		if(isEmpty() || nodo == null) 
			throw new InvalidPositionException(null);
		
		agregarEnElMedio(nodo.getAnterior(), nodo, element);
	}

	@Override
	public E remove(Position<E> p) {
		DNodo<E> nodo = checkPosition(p);
		
		if (isEmpty())
            throw new EmptyListException("Se intento remove en una lista vacia");
        
		
        
		nodo.getAnterior().setSiguiente(nodo.getSiguiente());
        nodo.getSiguiente().setAnterior(nodo.getAnterior());
        
        size--;
        
        return nodo.element();
	}

	@Override
	public E set(Position<E> p, E element) {
		DNodo<E> nodo = checkPosition(p);
        
		E viejo = nodo.element();
        
		nodo.setElemento(element);
		return viejo;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
            DNodo<E> actual = cabeza.getSiguiente();

            public boolean hasNext() {
                return actual != cola;
            }

            public E next() {
                if (!hasNext()) 
                	throw new NoSuchElementException();
                    
                	E elem = actual.element();
                    actual = actual.getSiguiente();
                
              return elem;
            }
		};
		
	}

	 public Iterable<Position<E>> positions() {
        PositionList<Position<E>> list = new ListaDoblementeEnlazada<Position<E>>();
        Position<E> i = first();
        
        if(!isEmpty())
        	while(i != last()) {
        		list.addLast(cola);
        		i = next(i);
        	}
        
        return list;
	 }
	
	protected void agregarEnElMedio(DNodo<E> anterior, DNodo<E> siguiente, E elem){
	    DNodo<E> nodo= new DNodo<E>(elem);
	        
	    nodo.setSiguiente(siguiente);
	    nodo.getSiguiente().setAnterior(nodo);
	        
	    anterior.setSiguiente(nodo);
	        
	    nodo.setAnterior(anterior);
	        
	    size++;
	}
	
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
        DNodo<E> n;
        
        if(p == null){
            throw new InvalidPositionException("Posicion Nula. Posicion Invalida");
        }
        if(isEmpty()){
            throw new EmptyListException("No se puede operar sobre una lista vacia ");
        }
        try {
            n = (DNodo<E>) p;
        } catch (ClassCastException e) {
            throw new InvalidPositionException("Posicion invalida");
        }
        return n;
	}
	
	
	@SuppressWarnings("unused")
	private void agregarElementos(E element1, E element2) {
		DNodo<E> nodoNuevoSegundo    = new DNodo<E>(element1);
		DNodo<E> nodoNuevoAnteultimo  = new DNodo<E>(element2);
		
		DNodo<E> nodoVacioAnteriorCola = new DNodo<E>(null); //La ocupan mas de dos condiciones es preferible que sea global en el metodo
		
		ListaDoblementeEnlazada<E> lista =  new ListaDoblementeEnlazada<E>();
		
		if(isEmpty()) {
			DNodo<E> nodoVacioFrenteCabeza = new DNodo<E>(null);
			
			lista.cabeza.addSiguiente(nodoVacioFrenteCabeza);
			lista.cola.addAnterior(nodoVacioAnteriorCola);
			
			lista.cabeza.getSiguiente().addSiguiente(nodoNuevoSegundo);
			lista.cola.getAnterior().addAnterior(nodoNuevoAnteultimo);
		}
		
		if(size == 1) {
			lista.cola.addAnterior(nodoVacioAnteriorCola);
			
			lista.cabeza.getSiguiente().addSiguiente(nodoNuevoSegundo);
			lista.cola.getAnterior().addAnterior(nodoNuevoAnteultimo); 
		}
		
		lista.cabeza.getSiguiente().addSiguiente(nodoNuevoSegundo);
		lista.cola.getAnterior().addAnterior(nodoNuevoAnteultimo);
	}
	
	/*Ejercicio 3:
Utilizando el iterador programado en el TDALista:
a. Dada una PositionList<E> l y un elemento genérico e1, escriba un método tal que determine si e1 se
encuentra en la lista l. Compare los elementos por equivalencia.
b. Dada una PositionList<E> l y un elemento genérico e1, escriba un método tal que retorne la cantidad de
veces que e1 se encuentra en la lista l. Compare los elementos por equivalencia.
c. Dada una PositionList<E> l, un elemento genérico x y un entero n, retorne verdadero si y sólo si el elemento x
se encuentra en la lista l al menos n veces. Compare los elementos por identidad.
*/

	public boolean CompararElementos(PositionList<E> l, E elemento) {
		boolean toReturn = false;
				
		for(E cursor: l) {
			toReturn = cursor.equals(elemento);
			}
		
		return toReturn;
	}
	
	public int AparacionesDeUnElemento(PositionList<E> l, E elemento) {
		int cant = 0;
		
		for(E cursor: l) {
			if(cursor.equals(elemento)) {
				cant++;
			}
		}
		return cant;
	}
	
	public boolean AparacionesDeUnElemento(PositionList<E> l, E x, int n) {
		boolean toReturn = false;
		
		toReturn = (CompararElementos(l, x) && ((AparacionesDeUnElemento(l, x)) == n));
			
		return toReturn;
	}
}
