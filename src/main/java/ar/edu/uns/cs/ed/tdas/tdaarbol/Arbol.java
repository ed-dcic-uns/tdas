package ar.edu.uns.cs.ed.tdas.tdaarbol;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidOperationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDE;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class Arbol<E> implements Tree<E> {

    protected int cant;
    protected TNode<E> root;

    //Constructor 
    public Arbol(){
        cant=0;
        root=null;
    }
    //Consultas 

    @Override
    public int size() {
        return cant;
    }

    @Override
    public boolean isEmpty(){
        return cant==0;
    }

    @Override
    public Iterator<E> iterator() {
        PositionList<E> lista= new ListaDE<>();
        preOrdenElementos(root,lista);
        return lista.iterator();
    }
    
    @Override
    public Iterable<Position<E>> positions() {
        PositionList<Position<E>> reList= new ListaDE<Position<E>>();
        preOrdenPositions(root,reList);
        return reList;
    }

    @Override
    public E replace(Position<E> v, E e) {
        TNode<E> nodo= checkPosition(v);
        E resultado= nodo.element();
        nodo.setElement(e);
        return resultado;
    }

    @Override
    public Position<E> root() {
        if(isEmpty()){
            throw new EmptyTreeException("El Arbol esta vacio");
        }
        return root;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        TNode<E> nodo= checkPosition(v);
        if(nodo==root){
            throw new BoundaryViolationException("La raiz no tiene un padre");
        }
        return nodo.getPadre();
    }

    @Override
    public Iterable<Position<E>> children(Position<E> v) {
        TNode<E> nodo= checkPosition(v);
        PositionList<Position<E>> list= new ListaDE<>();
        for(TNode<E> n: nodo.getHijos()){
            list.addLast(n);
        }
        return list;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        TNode<E> nodo= checkPosition(v);
        return !nodo.getHijos().isEmpty();
    }

    @Override
    public boolean isExternal(Position<E> v) {
        TNode<E> nodo = checkPosition(v);
        return nodo.getHijos().isEmpty();
    }

    @Override
    public boolean isRoot(Position<E> v) {
        TNode<E> nodo = checkPosition(v);
        return root== nodo;
    }

    @Override
    public void createRoot(E e) {
        if (root != null){
            throw new InvalidOperationException("El Arbol ya posee una raiz");
        }
        root= new TNode<E>(e, null);
        cant++;
    }

    @Override
    public Position<E> addFirstChild(Position<E> p, E e) {
        TNode<E> padre= checkPosition(p);
        TNode<E> hijo= new TNode<E>(e, padre);
        padre.getHijos().addFirst(hijo);
        cant++;
        return hijo;
    }

    @Override
    public Position<E> addLastChild(Position<E> p, E e) {
        TNode<E> padre= checkPosition(p);
        TNode<E> hijo= new TNode<E>(e, padre);
        padre.getHijos().addLast(hijo);
        cant++;
        return hijo;
    }

    @Override
    public Position<E> addBefore(Position<E> p, Position<E> rb, E e) {
        TNode<E> padre= checkPosition(p);
        TNode<E> hermanoDerecho= checkPosition(rb);
        if(hermanoDerecho.getPadre()!=padre){
            throw new InvalidPositionException("El padre no es el verdadero ");
        }
        TNode<E> nodo= new TNode<E>(e, padre);
        Iterator<Position<TNode<E>>> ite= padre.getHijos().positions().iterator();
        Position<TNode<E>> posBuscada= null;
        while(ite.hasNext()&& posBuscada==null){
            Position<TNode<E>> n= ite.next();
            if(n.element()== hermanoDerecho)
                posBuscada=n;
        }
        padre.getHijos().addBefore(posBuscada,nodo);
        cant++;
        return nodo;
    }

    @Override
    public Position<E> addAfter(Position<E> p, Position<E> lb, E e) {
        TNode<E> padre = checkPosition(p);
        TNode<E>  hermIzq = checkPosition(lb);
        if(hermIzq.getPadre()!= padre)
            throw new InvalidPositionException("El padre no es el verdadero Padre");
        TNode<E> nodo = new TNode<>(e, padre);
        Iterator<Position<TNode<E>>> ite = padre.getHijos().positions().iterator();
        Position<TNode<E>> posBusca= null;
        while (ite.hasNext() && posBusca==null){
            Position<TNode<E>> n = ite.next();
            if(n.element()==hermIzq)
                posBusca= n;
        }
        padre.getHijos().addAfter(posBusca, nodo);
        cant++;
        return nodo;
    }

    @Override
    public void removeExternalNode(Position<E> p) {
        TNode<E> nodo = checkPosition(p);		
		if(this.isInternal(nodo)) {throw new InvalidPositionException("El nodo no es externo");}
		if(this.root == nodo) {
			//el nodo externo es la raiz
			this.root = null;
		    cant = 0;
		} else {
			Iterator<Position<TNode<E>>> ite = nodo.getPadre().getHijos().positions().iterator();
			Position<TNode<E>> posBuscada = null;
			while(ite.hasNext()&&posBuscada==null) {
				Position<TNode<E>> pos = ite.next();
				if(pos.element() == nodo ) {posBuscada = pos;}
			}
			nodo.getPadre().getHijos().remove(posBuscada);
			cant--;
		}
    }

    @Override
    public void removeInternalNode(Position<E> p) {
        TNode<E> nodo = checkPosition(p);
        if(isExternal(nodo)){
            throw new InvalidPositionException("No es un nodo Interno");
        }
        if( nodo== root){
            if(nodo.getPadre().getHijos().size()>1){
                throw new InvalidPositionException("No puedo eliminar la raiz porque tiene muchos hijos");
            }
        }
        else{
            Iterator<Position<TNode<E>>> ite= nodo.getPadre().getHijos().positions().iterator();
            Position<TNode<E>> posBuscada= null;
            while (ite.hasNext()&& posBuscada==null){
                Position<TNode<E>> n= ite.next();
                if(n.element()==nodo){
                    posBuscada=n;
                }
            }
            for(TNode<E> hijo : nodo.getHijos() ){
                nodo.getPadre().getHijos().addBefore(posBuscada,hijo);
                hijo.setPadre(nodo.getPadre());
            }
            nodo.getPadre().getHijos().remove(posBuscada);
			nodo.setPadre(null);
			cant--;
        }
    }

    @Override
    public void removeNode(Position<E> p) {
        TNode<E> nodo= checkPosition(p);
        if(isInternal(nodo)){
            removeInternalNode(p);
        }
        else{
            removeExternalNode(p);
        }
    }

     private void preOrdenElementos(TNode<E> nodo, PositionList<E> l) {
        l.addLast(nodo.element());
        for(TNode<E> n:nodo.getHijos()){
            preOrdenElementos(n, l);
        }
    }
     private void preOrdenPositions(TNode<E> nodo, PositionList<Position<E>> reList) {
        reList.addLast(nodo);
        for(TNode<E> n: nodo.getHijos()){
            preOrdenPositions(n, reList);
        }
    }
    private TNode<E> checkPosition(Position<E> v) {
		TNode<E> resultado = null;
		if( v == null) {
            throw new InvalidPositionException("Posicion es nula");
        }
		if( this.isEmpty()) {
            throw new InvalidPositionException("El árbol está vacío");
        }
		try {
			resultado = (TNode<E>)v;
		} catch( ClassCastException e ) {
			throw new InvalidPositionException("La posición no es del tipo correcto");
		}
		return resultado;
	}
   
}
