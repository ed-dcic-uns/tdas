package ar.edu.uns.cs.ed.tdas.tdamapeo;

import java.util.Iterator;

import TDALISTA.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class TDAmapeoConLista<K,V> implements Map<K,V>{
    private PositionList<Entrada<K,V>> lista;
public TDAmapeoConLista(){
    lista= new ListaDoblementeEnlazada<Entrada<K,V>>();
}
public int size(){
    return lista.size();
}
public boolean isEmpty(){
    return size()==0;
}
public V getValue(K key){
    V resultado = null;
    if(key==null){throw new InvalidKeyException("HOLA");}
    else {

        Iterator<Entrada<K,V>> it = lista.iterator();
        boolean encontrado = false;
        while(it.hasNext()&&!encontrado){
            Entrada<K,V> ent = it.next();
            if(ent.getKey()== key){
                encontrado=true;
                resultado=ent.getValue();
            }
        }
    }
    return resultado;
}
public V put(K key,V Value){
     V resultado = null;
    if(key==null){throw new InvalidKeyException("HOLA");}
    else {

        Iterator<Entrada<K,V>> it = lista.iterator();
        boolean encontrado = false;
        while(it.hasNext()&&!encontrado){
            Entrada<K,V> ent = it.next();
            if(ent.getKey()== key){
                resultado = ent.getValue();
                ent.setValue(Value);
                encontrado=true;
            }
        }
        if(!encontrado){
            Entrada<K,V> entrada = new Entrada<K,V>(key,Value);
            lista.addLast(entrada);
            resultado = Value;
        }
    }
    return resultado;
}
public V remove(K key){
    V resultado = null;
    if(key == null) {throw new InvalidKeyException("La clave del remove es nula");}
    else{
    Iterator<Position<Entrada<K,V>>> ite = this.lista.positions().iterator();
    boolean eliminado = false;
        while(ite.hasNext()&&!eliminado){
            Position<Entrada<K,V>> elemento = ite.next();
            Entrada<K,V> k = elemento.element();
            if(key.equals(k)){
                resultado=k.getValue();
                eliminado=true;
                lista.remove(elemento);
            }
        }
    }
return resultado;
}
public Iterable<K> keys(){
    PositionList<K> llaves = new ListaDoblementeEnlazada<>();
    for(Entry<K,V> k : this.lista){
        llaves.addLast(k.getKey());
    }
    return llaves;
}
public Iterable<V> values(){
    PositionList<V> resultado = new ListaDoblementeEnlazada<V>();
		for(Entry<K,V> entrada : this.lista ) {
			resultado.addLast(entrada.getValue());
		}
		return resultado;
}
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> resultado = new ListaDoblementeEnlazada<Entry<K, V>>();
		for(Entry<K,V> entrada : this.lista ) {
			resultado.addLast(entrada);
		}
		return resultado;
	}
}
