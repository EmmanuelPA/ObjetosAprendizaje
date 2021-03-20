package controladores;

import java.util.List;

import dominio.NodoArbolReferencias;

public class ControladorArbolReferencias {
	 private static ControladorArbolReferencias instancia;
	    //private int numeroNodos;
	   // private ArrayList<NodoArbolReferencias> rutaNodos;
	    private NodoArbolReferencias nodoActual;
	    private NodoArbolReferencias nodoRaiz;

	    private ControladorArbolReferencias() {
	        
	        nodoRaiz= new NodoArbolReferencias("default",true);
	        nodoRaiz.establecerNodoPadre(nodoRaiz);
	        nodoActual=nodoRaiz;
	    }
	    
	    
	   
	            
	            
	    public void establecerNodoRaizDeArchivo(String nombreArbol){
	        GeneradorManifiesto generador= new GeneradorManifiesto();
	        NodoArbolReferencias nuevoNodoRaiz= generador.obtenerArbolDeArchivo(nombreArbol);
	        nodoRaiz=nuevoNodoRaiz;
	        nodoRaiz.establecerNodoPadre(nodoRaiz);
	        nodoActual=nodoRaiz; 
	    }
	    
	    public void inicializarArbolReferencias(){
	     nodoRaiz= new NodoArbolReferencias("default",true);
	     NodoArbolReferencias nodoArbolInicial = new NodoArbolReferencias(nodoRaiz.obtenerNombre(),true);
	     nodoArbolInicial.establecerNodoPadre(nodoRaiz);
	     nodoRaiz.obtenerListaNodos().add(nodoArbolInicial);
	     nodoRaiz.establecerNodoPadre(nodoRaiz);
	     nodoActual=nodoRaiz;   
	    }
	    
	    public NodoArbolReferencias obtenerNodoRaiz(){
	        return nodoRaiz;
	    }
	    
	    public void eliminarNodoHijo(int i){
	        nodoActual.obtenerListaNodos().remove(i);
	        
	    }

	    public static ControladorArbolReferencias obtenerInstancia() {
	        if (instancia == null) {
	            instancia = new ControladorArbolReferencias();
	        }
	        return instancia;

	    }

	    public void agregarRecursoAlNodoActual(String nombre, boolean tipo, String url) {
	        NodoArbolReferencias nuevoNodo = new NodoArbolReferencias(nombre, tipo, url);
	        nodoActual.obtenerListaNodos().add(nuevoNodo);
	    }
	    
	     public void agregarEstructuraAlNodoActual(String nombre, boolean tipo) {
	        NodoArbolReferencias nuevoNodo = new NodoArbolReferencias(nombre, tipo);
	        nuevoNodo.establecerNodoPadre(nodoActual);
	        nodoActual.obtenerListaNodos().add(nuevoNodo);
	        
	    }

	    public List<NodoArbolReferencias> obtenerHijosNodoActual() {
	        return (List<NodoArbolReferencias>) nodoActual.obtenerListaNodos();
	    }
	    
	    public NodoArbolReferencias obtenerNodoActual() {
	        return nodoActual;
	               
	    }
	    
	    public void establecerNodoActual(int posicionNuevoNodoActual) {
	        nodoActual=nodoActual.obtenerListaNodos().get(posicionNuevoNodoActual);
	        
	    }
	    public void regresarNodoAnterior(){
	         nodoActual=nodoActual.obtenerNodoPadre();    
	    }
	    
	    public void actualizarNodoHijoEstructura(int i, String nuevoNombre){
	        nodoActual.obtenerListaNodos().get(i).establecerNombre(nuevoNombre);
	        
	    }
	    
	     public void actualizarNodoHijoRecurso(int i, String nuevoNombre, String nuevaURL){
	        nodoActual.obtenerListaNodos().get(i).establecerNombre(nuevoNombre);
	         nodoActual.obtenerListaNodos().get(i).establecerURL(nuevaURL);
	        
	        
	    }
}
