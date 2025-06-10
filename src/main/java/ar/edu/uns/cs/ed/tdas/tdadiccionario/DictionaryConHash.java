package ar.edu.uns.cs.ed.tdas.tdadiccionario;

import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEntryException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDE;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Entrada;

public class DictionaryConHash <K,V> implements Dictionary<K,V> {

    protected ListaDE<Entrada<K,V>> []mapeo;
    protected int cant;

    public DictionaryConHash(){
        mapeo=(ListaDE<Entrada<K,V>>[]) new ListaDE[73];
        for(int i=0; i<mapeo.length;i++){
            mapeo[i]=new ListaDE<>();
        }
        cant=0;
    }
    public int hash(K key ){
        return Math.abs(key.hashCode() % mapeo.length);

    }

    @Override
    public int size() {
        return cant;
    }

    @Override
    public boolean isEmpty() {
        return cant==0;
    }

    @Override
    public Entry<K, V> find(K key) {
        Entry<K,V> resultado=null;
        if (key == null){ 
            throw new InvalidKeyException("Clave inválida");
        }
        else{
            int index = hash(key);
            for (Entry<K, V> entry : mapeo[index]) {
                if (entry.getKey()==(key)) {
                    resultado= entry;
                }
            }
        }
        return resultado;
    }

    @Override
    public Iterable<Entry<K, V>> findAll(K key) {
        ListaDE<Entry<K, V>> resultado = new ListaDE<>();
        if (key == null){
            throw new InvalidKeyException("Clave inválida");
        }
        else{
        int index = hash(key);
        for (Entrada<K,V> entry : mapeo[index]) {
            if (entry.getKey()== key) {
                resultado.addLast(entry);
            }
        }
        }
        return resultado;
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
       if (key == null){
            throw new InvalidKeyException("Clave inválida");
        }
        Entrada<K,V> nueva = new Entrada<>(key, value);
        int index = hash(key);
        mapeo[index].addLast(nueva);
        cant++;
        return nueva;
    }

    @Override
    public Entry<K, V> remove(Entry<K, V> e) {
        if (e == null)
            throw new InvalidEntryException("Entrada inválida");
        int index = hash(e.getKey());
        boolean encontrada = false;
        for (Position<Entrada<K, V>> pos : mapeo[index].positions()) {
            if (pos.element().equals(e)) {
                mapeo[index].remove(pos);
                cant--;
                encontrada = true;
            }
        }
        if (!encontrada)
            throw new InvalidEntryException("La entrada no está en el diccionario");
        return e;
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        ListaDE<Entry<K, V>> resultado = new ListaDE<>();
        for (ListaDE<Entrada<K, V>> lista : mapeo) {
            for (Entry<K, V> e : lista) {
                resultado.addLast(e);
            }
        }
        return resultado;
    }
    
}
