package ar.edu.uns.cs.ed.tdas.tdamapeo;

/**
 * Interfaz Entry. 
 * @author Cátedra de Estructuras de Datos, Departamento de Cs. e Ing. de la Computación, UNS.
 */
public interface Entry<K,V> {

	/**
	 * Devuelve la clave de la entrada
	 * @return La clave de la entrada
	 */
	public K getKey();

	/**
	 * Devuelve el valor de la entrada
	 * @return El valor de la entrada
	 */
	public V getValue();
}
