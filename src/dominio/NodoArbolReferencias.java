package dominio;

import java.io.Serializable;
import java.util.ArrayList;

public class NodoArbolReferencias implements Serializable{
    
   // private int id;
    private String nombre;
    private boolean tipo;
    private final boolean ESTRUCTURA=true,RECURSO=false;
    private String URL;
    private NodoArbolReferencias nodoPadre;
    private ArrayList<NodoArbolReferencias> listaNodos;
    private Metadatos metadatos;

   
   
    
    public NodoArbolReferencias(String nombre, boolean tipo, String URL){
     //  this.id=id;
       this.nombre=nombre;
       this.tipo=RECURSO;
       this.URL=URL;
       this.listaNodos = new ArrayList<NodoArbolReferencias>();
       this.metadatos=new Metadatos();
       
       
    }
    
    public NodoArbolReferencias(String nombre, boolean tipo){
      // this.id=id;
       this.nombre=nombre;
       this.tipo=ESTRUCTURA;
       this.listaNodos = new ArrayList<NodoArbolReferencias>();
       this.metadatos=new Metadatos();
    }
    
     public String obtenerURL() {
        return URL;
    }
     
    
    public int obtenerNumeroNodosHijos(){
        return listaNodos.size();
    }
    public void establecerURL(String URL) {
        this.URL = URL;
    }
    
     public NodoArbolReferencias obtenerNodoPadre() {
        return nodoPadre;
    }
     
    

    public void establecerNodoPadre(NodoArbolReferencias nodoPadre) {
        this.nodoPadre = nodoPadre;
    }
/*
    public int obtenerId() {
        return id;
    }

    public void establecerId(int id) {
        this.id = id;
    }
*/
    public ArrayList<NodoArbolReferencias> obtenerListaNodos() {
        return listaNodos;
    }

    public void establecerListaNodos(ArrayList<NodoArbolReferencias> lista_nodos) {
        this.listaNodos = lista_nodos;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void establecerNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean obtenerTipo() {
        return tipo;
    }

    public void establecerTipo(boolean tipo) {
        this.tipo = tipo;
    }
    
    @Override
	public String toString() {
		return nombre;
	}
    
    
     public Metadatos obtenerMetadatos() {
        return metadatos;
    }

    public void establecerMetadatos(Metadatos metadatos) {
        this.metadatos = metadatos;
    }
    
    /*@Override
	public String toString() {
		String tipoNodo = "";
		if(tipo == ESTRUCTURA) {
			tipoNodo = "ESTRUCTURA";
		}else {
			tipoNodo = "RECURSO";
		}
	    return "Nombre: "+nombre+" "+ "Tipo: "+tipoNodo;
    }*/
    
}
