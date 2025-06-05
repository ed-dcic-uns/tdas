package ar.edu.uns.cs.ed.tdas.tdamapeo;

import java.security.InvalidKeyException;
import java.util.Iterator;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDE;

public class MapeoConLista<K,V> implements Map<K,V> {

    private ListaDE<Entrada<K,V>> lista;

    public MapeoConLista(){
        lista= new ListaDE();
    }

    @Override
    public int size() {
        return lista.size();
    }

    @Override
    public boolean isEmpty() {
        return lista.isEmpty();
    }

    @Override
    public V get(K key) throws InvalidKeyException {
        V resultado= null;
        if(key==null){
            throw new InvalidKeyException("La clave del get es nula");
        }
        else{
            Iterator<Entrada<K,V>>ite= lista.iterator();
            boolean encontrada= false;
            while(ite.hasNext()&&!encontrada){
                Entrada<K,V> e=ite.next();
                if(e.getKey()==key){
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
        if(key==null){
            throw new InvalidKeyException("La clave del put es nula");
        }
        else{
            Iterator<Entrada<K,V>> ite= lista.iterator();
            boolean encontrada=false;
            while (ite.hasNext() && !encontrada){
                Entrada<K,V> e= ite.next();
                if(e.getKey()== key){
                    encontrada= true;
                    resultado= e.getValue();
                    e.setValue(value);
                }
            }
            if(!encontrada){
                Entrada<K,V> nueva= new Entrada(key, value);
                lista.addLast(nueva);
            }
        }

        return resultado;
    }

    @Override
    public V remove(K key) throws InvalidKeyException{
        V resultado= null;
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
                    e.setValue(null);
                }
            }
        }
        return resultado;
    }

    @Override
    public Iterable<K> keys() {
        ListaDE<K> claves = new ListaDE<>();
        for (Entrada<K, V> entrada : lista) {
            claves.addLast(entrada.getKey());
    }
    return claves;
    }

    @Override
    public Iterable<V> values() {
        ListaDE<V> valor= new ListaDE<>();
        for (Entrada<K,V> entrada : lista) {
            valor.addLast(entrada.getValue());
        }
        return valor;
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        ListaDE<Entry<K,V>> entradas= new ListaDE<>();
        for (Entrada<K,V> entrada : lista) {
           entradas.addLast(entrada); 
        }
        return entradas;
    }

    
}
