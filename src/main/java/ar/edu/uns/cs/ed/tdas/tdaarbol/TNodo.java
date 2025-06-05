package ar.edu.uns.cs.ed.tdas.tdaarbol;

import TDALISTA.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class TNodo<E> implements Position<E>{

	protected E elemento;
	protected TNodo<E> padre;
	protected PositionList<TNodo<E>> hijos;

	/**
	 * Constructor de TNodo.
	 * 
	 * Inicializa:
	 *  el elemento con el.
	 *  su padre, con p.
	 *  su lista de hijos, sin ningún elemento.
	 * @param el: elemento que almacenará.
	 * @param p: Nodo padre del nodo actual.
	 */
	public TNodo(E el, TNodo<E> p){
		elemento=el;
		padre=p;
		hijos= new ListaDoblementeEnlazada<TNodo<E>>();
	}
	
	
	
	/**
	 * @return padre del nodo actual.
	 */
	public TNodo<E> padre(){
		return padre;
	}
	
	/**
	 * @return 
	 * @return lista de hijos del nodo actual.
	 */
	public PositionList<TNodo<E>> getHijos(){
		return hijos;
	}
	
	/**
	 * Setea el padre del nodo actual.
	 * @param p: nodo padre.
	 */
	public void setPadre (TNodo<E> p){
		padre = p;
	}
	
	/**
	 * Setea el elemento del nodo actual.
	 * @param e elemento.
	 */
	public void setElement( E e) {
		elemento = e;
	}
	
	/**
	 * @return elemento del nodo actual.
	 */
	public E element() {
		return elemento;
	}
}