package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;

import ar.edu.uns.cs.ed.tdas.Position;

public class BTNode <E> implements Position <E> {

    private E element;
    private BTNode<E> padre, hderecho,hizquierdo;
    
    public BTNode(E elem, BTNode<E> hi, BTNode<E> hd, BTNode<E> padre){
        element = elem;
        hizquierdo = hi;
        hderecho = hd;
        this.padre = padre;

    }
    // Metodos 
    public void setElement(E elem){
        element= elem;
    }
    public void setPadre(BTNode<E> p){
        padre = p;
    }
    public void setHermandoDerecho(BTNode<E> hd){
        hderecho = hd;
    }
    public void setHermandoIzquierdo(BTNode<E> hi){
        hizquierdo = hi;
    }

    //Consultas
    public BTNode<E> getHDerecho(){
        return hderecho;
    }
    public BTNode<E> getHIzquierdo(){
        return hizquierdo;
    }
    public BTNode<E> getPadre(){
        return padre;
    }

    @Override
    public E element() {
        return element;
    }
    
}
