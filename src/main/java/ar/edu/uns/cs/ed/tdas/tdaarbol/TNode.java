package ar.edu.uns.cs.ed.tdas.tdaarbol;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDE;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class TNode <E> implements Position<E> {

    protected E element;
    protected TNode<E> padre;
    protected PositionList<TNode<E>> hijos;

    //Constructor 
    public TNode(E e, TNode<E> p){
        element=e;
        hijos=new ListaDE<>();
        padre=p;
    }
    //Metodos 
    public void setPadre(TNode<E> p){
        padre=p;
    }
    public void setElement(E e){
        element=e;
    }

    //Consultas
    public PositionList<TNode<E>> getHijos(){
        return hijos;
    } 
    public TNode<E> getPadre(){
        return padre;
    }
    @Override
    public E element() {
        return element;
    }
    
}
