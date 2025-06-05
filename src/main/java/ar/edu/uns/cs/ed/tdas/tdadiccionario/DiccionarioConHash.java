package ar.edu.uns.cs.ed.tdas.tdadiccionario;


import java.util.Iterator;

import TDALISTA.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Entry;

public class DiccionarioConHash<K,V> implements Dictionary<K,V> {
    protected ListaDoblementeEnlazada<Entry<K,V>> [] arreglo;
    protected int cantidad;
    protected int cubetas;
public DiccionarioConHash(){
    cantidad=0;
    cubetas=17;
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
public Entry<K,V> find(K key){
     Entry<K,V> resultado = null;
    if(key==null) { throw new InvalidKeyException("Clave nula en find");}
    else{
        int cubeta=hash(key);
        PositionList<Entry<K, V>> lista = this.arreglo[cubeta];
        Iterator<Entry<K,V>> it = lista.iterator();
        boolean encontrado = false;
        while(it.hasNext()&&!encontrado){
            resultado=it.next();
            if(resultado.getKey()==key)
            encontrado=true;
        }
    }
    return resultado;
}
private int hash(K key){
  return key.hashCode()%cubetas;
}
public Iterable<Entry<K,V>> findAll(K key){
    ListaDoblementeEnlazada<Entry<K,V>> resultado = new ListaDoblementeEnlazada<>();
    if(key == null){throw new InvalidKeyException("LLvme incorrecta");}
    else{
        for(int i=0;i<cubetas;i++){
            for(Entry<K,V> k : arreglo[i]){
                if(k.getKey()==k)
                resultado.addLast(k);
            }
        }    
    }
    return resultado;
}
public Entry<K,V> insert(K key, V value){
    if(key==null){throw new InvalidKeyException("clave incorrecta");}
        Entry<K,V> resultado = new Entrada(key,value);
        int cubeta=hash(key);
        ListaDoblementeEnlazada<Entry<K,V>> insertado = arreglo[cubeta];
        insertado.addLast(resultado);
    return resultado;
}
public Entry<K,V> remove(Entry<K,V> e){
    if (e == null) {
        throw new InvalidKeyException("Entrada nula");
    }

    int cubeta = hash(e.getKey());
    PositionList<Entry<K,V>> lista = arreglo[cubeta];

    for (Position<Entry<K,V>> p : lista.positions()) {
        if (p.element() == e) {             // misma instancia de entrada
            lista.remove(p);                // O(1) en lista doble
            cantidad--;                     // mantiene size() coherente
            
        }
    }
    return e;
}
public Iterable<Entry<K,V>> entries() {
    // 1. Creo una lista vacía donde iré copiando las referencias:
    PositionList<Entry<K,V>> todas = new ListaDoblementeEnlazada<>();

    // 2. Recorro cada cubeta y agrego sus entradas:
    for (int i = 0; i < cubetas; i++) {
        for (Entry<K,V> e : arreglo[i]) {   // la lista-cubeta ya es Iterable
            todas.addLast(e);               // O(1) por llamada
        }
    }
    // 3. Devuelvo la colección resultante (Iterable por la interfaz de la lista)
    return todas;                           // Θ(n) tiempo, Θ(n) memoria
}
public Iterable<Entry<K,V>> eliminarTodas(K c,V v){
        ListaDoblementeEnlazada<Entry<K,V>> resultado = new ListaDoblementeEnlazada<>();
    if(c == null){throw new InvalidKeyException("LLvme incorrecta");}
    else{
        for(int i=0;i<cubetas;i++){
            for(Entry<K,V> k : arreglo[i]){
                if(k.getKey()==k&&k.getValue()==v)
                remove(k);
                resultado.addLast(k);
            }
        }    
    }
    return resultado;
}
}

