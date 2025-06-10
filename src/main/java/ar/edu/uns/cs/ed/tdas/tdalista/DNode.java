package ar.edu.uns.cs.ed.tdas.tdalista;
import ar.edu.uns.cs.ed.tdas.Position;
public class DNode<E> implements Position<E>  {

    protected E elem;
    protected DNode<E> prev, next;

    public DNode(E elemento){
        elem=elemento;
        next=null;
        prev=null;
    }
    public DNode( E elemento, DNode<E> sig ) {
		elem = elemento;
		next = sig;
	}
    //Metodos 
    public void setElemento(E elem){
        this.elem=elem;
    }
    public void setSiguiente(DNode<E> sig){
        next=sig;
    }
    public void setAnterior(DNode<E> ante){
        prev=ante;
    }
    // Consultas 
    public E getElemento(){
        return elem;
    }
    
    public DNode<E> getSiguiente(){
        return next;
    }

    public DNode<E> getAnterior(){
        return prev;
    }
    public E element(){
        return elem;
    }
    
    
}
