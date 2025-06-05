package ar.edu.uns.cs.ed.tdas.tdaarbol;

import java.util.Iterator;

import TDALISTA.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class TDAarbol<E> implements Tree<E>{
    private TNodo<E> root;
    private int size;
public TDAarbol(){
    root=null;
    size=0;
}
public int size(){
    return size;
}
public boolean isEmpty(){
    return size==0;
}
public TNodo<E> root(){
    return root;
}
public Iterator<E> iterator(){
  PositionList<E> resultado = new ListaDoblementeEnlazada<E>();
		preordenElementos(root, resultado);
		return resultado.iterator();
}
protected void preordenElementos( TNodo<E> nodo, PositionList<E> lista ) {
		lista.addLast(nodo.element()); //Visitar nodo
		for(TNodo<E> n : nodo.getHijos()) {
			preordenElementos(n, lista);
		}
}
public Iterable<Position<E>> positions(){
    PositionList<Position<E>> resultado = new ListaDoblementeEnlazada<Position<E>>();
    preordenPositions(root,resultado);
    return resultado;
}
private void preordenPositions(TNodo<E> p2,PositionList<Position<E>> lista){
    lista.addLast(p2);
    for(TNodo<E> p1 : p2.getHijos()){
        preordenPositions(p1,lista);
    }
}
public E replace(Position<E> v,E e){
   TNodo<E> nodo = checkPosition(v);
   E resultado = nodo.element();
   nodo.setElement(e);
   return resultado;
}
private TNodo<E> checkPosition(Position<E> v) {
		TNodo<E> resultado = null;
		if( v == null) {throw new InvalidPositionException("Posicion es nula");}
		if( this.isEmpty()) {throw new InvalidPositionException("El árbol está vacío");}
		try {
			resultado = (TNodo<E>)v;
		} catch( ClassCastException e ) {
			throw new InvalidPositionException("La posición no es del tipo correcto");
		}
		return resultado;
}
public TNodo<E> parent(Position<E> p){
    TNodo<E> nodo=checkPosition(p);
    return nodo.padre();
}
public Iterable<Position<E>> children(Position<E> v){
    TNodo<E> nodo = checkPosition(v);
    PositionList<Position<E>> resultado = new ListaDoblementeEnlazada<>();
    for(TNodo<E> t1 : nodo.getHijos()){
        resultado.addLast(t1);
    }
    return resultado;
}
}
