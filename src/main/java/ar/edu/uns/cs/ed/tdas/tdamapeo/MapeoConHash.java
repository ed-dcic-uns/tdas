package ar.edu.uns.cs.ed.tdas.tdamapeo;

import java.security.InvalidKeyException;
import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDE;

public class MapeoConHash<K,V> implements Map<K,V> {
    
    private ListaDE<Entrada<K,V>> [] arry ;
    private int cant;

    public MapeoConHash(){
        arry = (ListaDE<Entrada<K,V>>[]) new ListaDE[73];
        for (int i = 0; i < arry.length; i++) {
            arry[i] = new ListaDE<>();
        }
        cant = 0;
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
    public V get(K key) throws InvalidKeyException {
        V resultado= null;
        ListaDE<Entrada<K,V>>lista=arry[h(key)];
        if(key==null){
            throw new InvalidKeyException("La clave del get es nula");
        }
        else{
            Iterator<Entrada<K,V>>ite= lista.iterator();
            boolean encontrada= false;
            while(ite.hasNext()&&!encontrada){
                Entrada<K,V> e=ite.next();
                if(e.getKey().equals(key)){
                    encontrada=true;
                    resultado= e.getValue();
                }
            }    
        }
        return resultado;
    }

    @Override
    public V put(K key, V value) throws InvalidKeyException {
        V resultado= null;
        ListaDE<Entrada<K,V>>lista=arry[h(key)];
        if(key==null){
            throw new InvalidKeyException("La clave del put es nula");
        }
        else{
            Iterator<Entrada<K,V>> ite= lista.iterator();
            boolean encontrada=false;
            while (ite.hasNext() && !encontrada){
                Entrada<K,V> e= ite.next();
                if(e.getKey().equals(key)){
                    encontrada= true;
                    resultado= e.getValue();
                    e.setValue(value);
                }
            }
            if(!encontrada){
                Entrada<K,V> nueva= new Entrada(key, value);
                lista.addLast(nueva);
                cant++;
            }
        }

        return resultado;
    }

    @Override
    public V remove(K key) throws InvalidKeyException{
        V resultado= null;
        ListaDE<Entrada<K,V>>lista=arry[h(key)];
        if(key==null){
            throw new InvalidKeyException("La clave del remove es nula");
        }
        else{
            Iterator<Entrada<K,V>> ite= lista.iterator();
            boolean encontrada=false;
            while(ite.hasNext()&&!encontrada){
                Entrada<K,V> e= ite.next();
                if(e.getKey()==key){
                    encontrada=true;
                    resultado=e.getValue();
                    ite.remove();
                    cant--;
                }
            }
        }
        return resultado;
    }

    @Override
    public Iterable<K> keys() {
          ListaDE<K> claves = new ListaDE<>();
        for (ListaDE<Entrada<K, V>> lista : arry) {
            for (Entrada<K, V> e : lista) {
                claves.addLast(e.getKey());
            }
        }
        return claves;
    }

    @Override
    public Iterable<V> values() {
        ListaDE<V> valores = new ListaDE<>();
        for (ListaDE<Entrada<K,V>> lista : arry) {
            for (Entrada<K,V> entrada : lista) {
                valores.addLast(entrada.getValue());
            }
        }
        return valores;
    }
    @Override
    public Iterable<Entry<K, V>> entries() {
        ListaDE<Entry<K,V>> entradas = new ListaDE<>();
        for (ListaDE<Entrada<K,V>> lista : arry) {
            for (Entrada<K,V> entrada : lista) {
                entradas.addLast(entrada);
            }
        }
        return entradas;
    }   
    protected int h(K key){
        return key.hashCode() % 73;
    } 
    
}
