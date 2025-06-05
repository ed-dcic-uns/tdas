package ar.edu.uns.cs.ed.tdas;

/**
 * Interface Position
 * @author Cátedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computación, UNS.
 */
public interface Position<E> {

	/**
	 * Devuelve el elemento en la posición
	 * @return El elemento en la posición
	 */
	public E element();

    public Position<E>[] getHijos();
	
}
