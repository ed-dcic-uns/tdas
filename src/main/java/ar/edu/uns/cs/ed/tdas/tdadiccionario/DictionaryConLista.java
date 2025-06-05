package ar.edu.uns.cs.ed.tdas.tdadiccionario;

import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;

import java.util.Iterator;
import ar.edu.uns.cs.ed.tdas.Position;

import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDE;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Entrada;

public class DictionaryConLista<K,V> implements Dictionary<K,V> {

    protected ListaDE<Entrada<K,V>> lista;

    public DictionaryConLista(){
        lista= new ListaDE<Entrada<K,V>>();
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
    public Entry<K, V> find(K key) {
        Entry<K,V> toReturn =null;
        if(key==null)
            throw new InvalidKeyException("Clave nula en find");
        else{
            boolean encontre= false;
            Entrada<K,V> entrada;
            Iterator<Entrada<K,V>> ite= lista.iterator();
            while(ite.hasNext()&&!encontre){
                entrada= ite.next();
                if(entrada.getKey()==key){
                    toReturn= entrada;
                    encontre=true;
                }
            }
        }
        return toReturn;
    }

    @Override
    public Iterable<Entry<K, V>> findAll(K key) {
       if(key==null){
            throw new InvalidKeyException("Clave nula en findAll");
       }
       else{
            PositionList<Entry<K,V>> resultado= new ListaDE<Entry<K,V>>();
            for(Entry<K,V>entrada : lista){
                if(entrada.getKey()==key)
                    resultado.addLast(entrada);
            }
            return resultado;
       }
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        if(key== null)
            throw new InvalidKeyException("Clave nula en insert");
        else{
            boolean encontre=false;
            Entrada<K,V> resultado=null;
            Iterator<Entrada<K,V>> ite= lista.iterator();
            while (ite.hasNext()&&!encontre){
                resultado= ite.next();
                if(resultado.getKey()==key && resultado.getValue()==value)
                    encontre=true;
            }
            if(!encontre){
                resultado= new Entrada<>(key, value);
                lista.addLast(resultado);
            }
            return resultado;
        }
    }

    @Override
    public Entry<K, V> remove(Entry<K, V> e) {
        if(e.getKey()==null)
            throw new InvalidKeyException("Clave nula en remove");
        else{
            boolean encontre=false;
            Entry<K,V> resultado = null;
			Iterator<Position<Entrada<K,V>>> ite = lista.positions().iterator();
            while (ite.hasNext() && !encontre) {
                Position<Entrada<K,V>> pos = ite.next();
                if(pos.element().getKey()== e.getKey() && pos.element().getValue()==e.getValue()){
                    encontre=true;
                    resultado=pos.element();
                    lista.remove(pos);
                }
            }
            if(!encontre)
                throw new NonExistanEntryExepcion("No esta la entry");
            else{
                return resultado;
            }
        }
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        return null;
    }
    
}
