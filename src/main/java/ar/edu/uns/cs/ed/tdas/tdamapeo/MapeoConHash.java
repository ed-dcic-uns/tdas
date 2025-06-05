package ar.edu.uns.cs.ed.tdas.tdamapeo;
import java.security.InvalidKeyException;
import java.util.Iterator;
import tdamapeo.Entry;
import TDALISTA.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdadiccionario.Entrada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
public class MapeoConHash<K,V> implements Map<K,V>{
  protected int cantidad;
  protected int cubetas;
  protected PositionList<Entry<K,V>> [] arreglo;
public MapeoConHash(){
  this.cantidad=0;
  this.cubetas=17;
  arreglo = new ListaDoblementeEnlazada[cubetas];
  for(int i = 0 ;i<this.cubetas;i++){
    this.arreglo[i] = new ListaDoblementeEnlazada<Entry<K,V>>();
  }
}
public int size(){
  return cantidad;
}
public boolean isEmpty(){
  return cantidad==0;
}
public V get(K key) {
    int cubeta = hash(key);
    PositionList<Entry<K, V>> lista = this.arreglo[cubeta];
    if (lista == null) {           // cubeta vacía
        return null;
    }

    for (Entry<K, V> ent : lista) {   // usa el for-each de tu lista
        if (key.equals(ent.getKey())) { // compara por igualdad lógica
            return ent.getValue();      // encontramos y salimos
        }
    }
    return null;                        // no estaba la clave
}

private int hash(K key){
  return key.hashCode()%cubetas;
}
public V put(K key,V value){
  if(key==null){throw new InvalidKeyException("key no valida");}
}
}
