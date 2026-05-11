package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Tree;

/**
 * Interface BinaryTree
 * Extiende la interfaz Tree.
 * @author Cátedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computación, UNS.
 */
public interface BinaryTree<E> extends Tree<E>
{
	/**
	 * Devuelve la posición del hijo izquierdo de v.
	 * @param v Posición de un nodo.
	 * @return Posición del hijo izquierdo de v.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 * @throws BoundaryViolationException si v no tiene hijo izquierdo.
	 */
	public Position<E> left(Position<E> v);
	
	/**
	 * Devuelve la posición del hijo derecho de v.
	 * @param v Posición de un nodo.
	 * @return Posición del hijo derecho de v.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 * @throws BoundaryViolationException si v no tiene hijo derecho.
	 */
	public Position<E> right(Position<E> v);

	/**
	 * Testea si v tiene un hijo izquierdo.
	 * @param v Posición de un nodo.
	 * @return Verdadero si v tiene un hijo izquierdo y falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.	
	 */
	public boolean hasLeft(Position<E> v);
	
	
	/**
	 * Testea si v tiene un hijo derecho.
	 * @param v Posición de un nodo.
	 * @return Verdadero si v tiene un hijo derecho y falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.	
	 */
	public boolean hasRight(Position<E> v);
	
	/**
	 * Agrega un nodo con rótulo r como hijo izquierdo de un nodo dado.
	 * @param r Rótulo del nuevo nodo.
	 * @param v Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 * @throws InvalidOperationException si v ya tiene un hijo izquierdo.
	 */
	public Position<E> addLeft(Position<E> v, E r);


	/**
	 * Agrega un nodo con rótulo r como hijo derecho de un nodo dado.
	 * @param r Rótulo del nuevo nodo.
	 * @param v Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 * @throws InvalidOperationException si v ya tiene un hijo derecho.
	 */
	public Position<E> addRight(Position<E> v, E r);

	/**
	 * Inserta a los árboles T1 y T2 como subárboles hijos de la hoja v (izquierdo y derecho respectivamente).
	 * @param v Posición de una hoja del árbol.
	 * @param T1 Árbol binario a insertar como hijo izquierdo de v.
	 * @param T2 Árbol binario a insertar como hijo derecho de v. 
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío, o v no corresponde a una hoja.
	 */
	public void attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2);
	
}

	
