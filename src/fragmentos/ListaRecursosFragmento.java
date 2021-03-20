/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fragmentos;

import controladores.ControladorArbolReferencias;
import dominio.NodoArbolReferencias;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import vistas.lista_fragmento;

/**
 *
 * @author Estrella
 */
public class ListaRecursosFragmento extends JList{
     
    private OnSelectedListener selectedListener;
    private List<NodoArbolReferencias> listaNodos;
   // private ArrayAdapter<NodoArbolReferencias> adaptador;
    private ControladorArbolReferencias controlador;
   
    //Prueba
    private lista_fragmento lista_fragmento; //= new lista_fragmento();
    private JList listaObjeto = new JList();
    DefaultListModel modelo = new DefaultListModel();
    
    public void onListItemClick(int position){
       
       NodoArbolReferencias nodoSeleccionado = listaNodos.get(position);
        if (nodoSeleccionado.obtenerTipo() == true) {
            controlador = ControladorArbolReferencias.obtenerInstancia();
            controlador.establecerNodoActual(position);
            //System.out.println(listaNodos.get(position).obtenerNombre());
            //actualizarEtiquetaNodoActual();
            cambiarLista();
        } else if (nodoSeleccionado.obtenerTipo() == false) {
            selectedListener.onItemSelected(listaNodos.get(position).obtenerURL());
        }
   }
    
    
    
     public void actualizarLista() {
        /*listaNodos=ControladorArbolReferencias.obtenerInstancia().obtenerHijosNodoActual();
        
         adaptador.notifyDataSetChanged();*/
        // setListAdapter(new AdaptadorImagenTexto(this.getActivity()));
        listaNodos = ControladorArbolReferencias.obtenerInstancia().obtenerHijosNodoActual();
        
       /* AdaptadorImagenTexto adapter = new AdaptadorImagenTexto(this.getActivity(), listaNodos);
        setListAdapter(adapter);*/
    }
     
     public void cambiarLista() {
        /* listaNodos=ControladorArbolReferencias.obtenerInstancia().obtenerHijosNodoActual();
         adaptador = new ArrayAdapter<NodoArbolReferencias>(this.getActivity(),
         R.layout.elemento_lista_recursos, listaNodos);
         setListAdapter(adaptador);*/
       /* lista_fragmento = lista_fragmento.obtenerInstancia();
        listaObjeto = lista_fragmento.getListaObjeto();*/
        
        
        listaNodos = ControladorArbolReferencias.obtenerInstancia().obtenerHijosNodoActual();
       /* if(listaNodos.isEmpty()){
            listaObjeto.setModel(modelo);
        }else{
            for(int i=0;i<=listaNodos.size();i++){
            modelo.addElement(listaNodos.get(i).obtenerNombre());
        }
        }
       /* for(int i=0;i<=listaNodos.size();i++){
            modelo.addElement(listaNodos.get(i).obtenerNombre());
        }*/
         //System.out.println(listaNodos.size());
         

        /* adaptador = new ArrayAdapter<NodoArbolReferencias>(this.getActivity(),
         R.layout.elemento_lista_recursos, listaNodos);
         setListAdapter(adaptador);*/
        //AdaptadorImagenTexto adapter = new AdaptadorImagenTexto(this.getActivity(), listaNodos);
        //setListAdapter(adapter);
    }
     
    
     
     public interface OnSelectedListener {

        public void onItemSelected(String tutUrl);
    }
    
}
