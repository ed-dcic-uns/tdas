package ar.edu.uns.cs.ed.tdas.tdamapeo;

import ar.edu.uns.cs.ed.tdas.Entry;

public class Entrada <K,V> implements Entry<K,V> {
    private K clave;
    private V valor;

    public Entrada(K clave, V valor){
        this.clave= clave;
        this.valor= valor;
    }
    @Override
    public K getKey() {
       return clave;
    }
    @Override
    public V getValue() {
        return valor;
    }
    public void setKey(K key){
        clave= key;
    }
    public void setValue(V value){
        valor= value;
    }
    public String toString(){
        return("Clave: "+getKey()+" ; Valor: "+getValue());
    }
}
