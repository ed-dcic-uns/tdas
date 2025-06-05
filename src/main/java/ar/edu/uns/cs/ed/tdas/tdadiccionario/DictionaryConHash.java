package ar.edu.uns.cs.ed.tdas.tdadiccionario;

import ar.edu.uns.cs.ed.tdas.Entry;
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
        return key.hashCode() % 73;
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
        if (key == null) 
            throw new InvalidKeyException("Clave inválida");
        Entry<K,V> resultado=null;
        int index = hash(key);
        for (Entry<K, V> entry : mapeo[index]) {
            if (entry.getKey()==(key)) {
                resultado= entry;
            }
        }
        return resultado;
    }

    @Override
    public Iterable<Entry<K, V>> findAll(K key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public Entry<K, V> remove(Entry<K, V> e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'entries'");
    }
    
}
