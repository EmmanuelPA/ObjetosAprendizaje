/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividades;

import javafx.scene.web.WebView;
import com.sun.jndi.toolkit.url.Uri;
import controladores.ControladorArbolReferencias;
import controladores.GeneradorManifiesto;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import vistas.biblioteca_objetos;
import vistas.lista_fragmento2;
import dominio.NodoArbolReferencias;
import fragmentos.ListaRecursosFragmento;
import fragmentos.VistaRecursoFragmento;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JSeparator;
/**
 *
 * @author Estrella
 */
public class ListaRecursos implements ListaRecursosFragmento.OnSelectedListener {
    private lista_fragmento2 lista_fragmento;// = new lista_fragmento();//Vista
    private static ListaRecursos ListaRecursos;
    private JTree arbol;
    DefaultListModel modelo = new DefaultListModel();
    DefaultMutableTreeNode rootNode;
    DefaultTreeModel treeModel;
    ControladorArbolReferencias controladorArbol;
    private int codigo_respuesta = 0;
    private final int CODIGO_AGREGAR_RECURSO = 1;
    private final int CODIGO_AGREGAR_ESTRUCTURA = 2;
    private final int CODIGO_EDITAR_RECURSO = 3;
    private final int CODIGO_EDITAR_ESTRUCTURA = 4;
    private boolean banderaEdicion;
    private int posicionElementoSeleccionado;
    private String nombreObjeto;
    private String edicion;
    JButton salir = new JButton();
    JButton guardar = new JButton();
    JButton recurso = new JButton();
    JButton estructura = new JButton();

    ListaRecursosFragmento lista;
    
    
    private ListaRecursos(){
        
    }
    
   
    public static ListaRecursos obtenerInstancia(){
        if(ListaRecursos == null){
            ListaRecursos = new ListaRecursos();
        }
        return ListaRecursos;
    }
    
    
    
     public void setnombreObjeto(String nombreObjeto){
         
        this.nombreObjeto = nombreObjeto;
        
    }
     
    public void setEdicionObjeto(String edicionObjeto){
        this.edicion = edicionObjeto;
    }
     
    
    public void iniciarComponentes(){
         lista_fragmento= lista_fragmento.obtenerInstancia();
        //Los botones estaban delcarados aquí 
         salir = lista_fragmento.getBotonSalir();
         guardar = lista_fragmento.getBotonGuardar();
         recurso = lista_fragmento.getBotonRecruso();
         estructura = lista_fragmento.getBotonEstructura();
        
         
         controladorArbol = ControladorArbolReferencias.obtenerInstancia();
         
         controladorArbol.inicializarArbolReferencias();
         banderaEdicion = false;
         lista = new ListaRecursosFragmento();
        lista.actualizarLista();
         if(nombreObjeto != null){
              controladorArbol.obtenerNodoRaiz().establecerNombre("raiz");
              controladorArbol.obtenerNodoRaiz().obtenerListaNodos().get(0).establecerNombre(nombreObjeto);
              if(edicion != null){
                   controladorArbol.establecerNodoRaizDeArchivo(nombreObjeto);
                    lista.actualizarLista();
                    crearArbolRecursoExistente();
              }else{
                    arbol = lista_fragmento.getArbolObjetos();
                    rootNode = lista_fragmento.getRootNode();
                    rootNode.setUserObject(controladorArbol.obtenerNodoRaiz().obtenerListaNodos().get(0));
                    treeModel = lista_fragmento.getTreeModel();
                    treeModel.setRoot(rootNode);
                    arbol.setModel(treeModel);
              }
         }
         
         
         arbol.setToggleClickCount(1);
         //arbol.setShowsRootHandles(true);
         arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
         /*arbol.addTreeSelectionListener(new TreeSelectionListener(){
              public void valueChanged(TreeSelectionEvent e){
                   int elementoSeleccionado;
                   DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbol.getSelectionPath().getLastPathComponent();
                   System.out.println(node);
                   if(controladorArbol.obtenerNodoActual().obtenerNodoPadre().equals(node)){
                       retroceder();
                        if(node != arbol.getModel().getRoot()){
                       elementoSeleccionado = node.getParent().getIndex(node);
                   }else{
                       elementoSeleccionado = 0;
                   }
                   }else{
                        if(node != arbol.getModel().getRoot()){
                       elementoSeleccionado = node.getParent().getIndex(node);
                   }else{
                       elementoSeleccionado = 0;
                   }
                   }

                  System.out.println(elementoSeleccionado);
                 
                   lista.onListItemClick(elementoSeleccionado);
                 /* TreeSelectionModel model = arbol.getSelectionModel();
                   int numero = model.getSelectionRows()[0];
                   System.out.println(numero);
                    lista.onListItemClick(numero);
              }
         });*/
         
         arbol.addMouseListener(new MouseListener(){
              @Override
              public void mouseClicked(MouseEvent e) {
                   int row=arbol.getRowForLocation(e.getX(),e.getY());
                    if(row==-1) //When user clicks on the "empty surface"
                        arbol.clearSelection();
                  
                  
                   int elementoSeleccionado;
                   DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbol.getSelectionPath().getLastPathComponent();
                  NodoArbolReferencias nar = NodoArbolReferencias.class.cast(node.getUserObject());
                   if(controladorArbol.obtenerNodoActual().obtenerNodoPadre().equals(nar)){
                       retroceder();
                        if(node != arbol.getModel().getRoot()){
                       elementoSeleccionado = node.getParent().getIndex(node);
                   }else{
                       elementoSeleccionado = 0;
                   }
                   }else{
                        if(node != arbol.getModel().getRoot()){
                       elementoSeleccionado = node.getParent().getIndex(node);
                   }else{
                       elementoSeleccionado = 0;
                   }
                   }
                   //Tal vez aquí si debo crear otro nuevo objeto de lista.
                   
                   lista.onListItemClick(elementoSeleccionado);
                   
              }

              @Override
              public void mousePressed(MouseEvent me) {
                   
              }

              @Override
              public void mouseReleased(MouseEvent me) {
                 
              }

              @Override
              public void mouseEntered(MouseEvent me) {
                 
              }

              @Override
              public void mouseExited(MouseEvent me) {
                  
              }
         
         
         });
         
         salir.setFocusable(false);
         guardar.setFocusable(false);
         recurso.setFocusable(false);
         estructura.setFocusable(false);
         
          guardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
                //agregarNuevoObjeto(nombreObjeto);
                guardarObjetoAprendizaje();
              
               //lista_fragmento.dispose();
               
              
            }
            
        });
          
         salir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                mostrarOpcionesGuardado();
            
            }
         });
         
         recurso.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent ae) {
                 if(controladorArbol.obtenerNodoActual().equals(controladorArbol.obtenerNodoRaiz())){
                      JOptionPane.showMessageDialog(null, "No se puede agregar un recurso, te encuentras fuera del objeto",
                        "Hey!", JOptionPane.ERROR_MESSAGE);
                 }else{
                      agregarRecurso();
                 }
                 arbol.clearSelection();
             }
             
         });
         
         estructura.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent ae) {
               if(controladorArbol.obtenerNodoActual().equals(controladorArbol.obtenerNodoRaiz())){
                    JOptionPane.showMessageDialog(null, "No se puede agregar una estructura, te encuentras fuera del objeto",
                        "Hey!", JOptionPane.ERROR_MESSAGE);
               }else{
                   mostrarDialogoAgregarEstructura(null);
               }
               arbol.clearSelection();
             }
         
         });

         lista_fragmento.setLocationRelativeTo(null);
         lista_fragmento.setVisible(true);
         lista_fragmento.getContentPane().setSize(400, 300);
         lista_fragmento.setTitle("LOTUS");
         //lista_fragmento.pack();
         lista_fragmento.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
    
    
    
    
    private void guardarObjetoAprendizaje(){
         GeneradorManifiesto generadorManifiesto = new GeneradorManifiesto();
        generadorManifiesto.generarXML();
        generadorManifiesto.generarArchivoArbolSerializable();
    }
 
    
   private void agregarNuevoObjeto(String nombreObjeto){
       
        GeneradorManifiesto generador = new GeneradorManifiesto();
        ControladorArbolReferencias controlador = ControladorArbolReferencias.obtenerInstancia();
        controlador.agregarEstructuraAlNodoActual(nombreObjeto, true);
        controlador.establecerNodoActual(0);
        generador.generarXML();
        generador.generarArchivoArbolSerializable();
         
    }
   
   private void mostrarOpcionesGuardado(){
                      /*lista_fragmento.dispose();
               new BibliotecaObjetos();*/
                 String mensaje;
                if (banderaEdicion == true) {
                    mensaje = "¿Desea guardar los cambios realizados?";
                } else {
                    mensaje = "¿Desea salir?";
                }
                
               int resp = JOptionPane.showConfirmDialog(null,
                                                        mensaje,
                                                        "Confirmación" , JOptionPane.YES_NO_OPTION);

                        if( resp == JOptionPane.YES_OPTION )
                        {
                          if (banderaEdicion == true) {
                            //guardar y salir
                            guardarObjetoAprendizaje();
                            banderaEdicion = false;
                            mostrarOpcionesGuardado();
                        } else {
                              lista_fragmento.dispose();
                                new BibliotecaObjetos();
                        }
                        }else if(resp == JOptionPane.NO_OPTION){
                            if (banderaEdicion == true) {
                                banderaEdicion = false;
                                mostrarOpcionesGuardado();
                            }
                        }
   }
   
   public void mostrarDialogoAgregarEstructura(String nombreEstructura){
       //Primer intento 
         JTextField userInput = new JTextField();
        if(nombreEstructura != null ){
            userInput.setText(nombreEstructura);
       }else{
            banderaEdicion=false;
       }
       int result = JOptionPane.showConfirmDialog(null, userInput, "Agregar Estructura", JOptionPane.PLAIN_MESSAGE);
//Lo correcto seria armar una vista, nuevo joption pane y un area de texto para obtener el texto y los botones.
       if (result == JOptionPane.OK_OPTION){
           String nombreEstruc = userInput.getText().toString();
           if(!userInput.getText().toString().equals("")){
               if(banderaEdicion==true){
                   controladorArbol.actualizarNodoHijoEstructura(posicionElementoSeleccionado, nombreEstruc);
                   lista.actualizarLista();
                   banderaEdicion = false;
                   
               }else{
                   //No debo enviar true?
                   controladorArbol.agregarEstructuraAlNodoActual(nombreEstruc, false);
                   addObject(nombreEstruc);
                   //Tengo que crear una lista nueva por lo que veo
                   ListaRecursosFragmento lista = new ListaRecursosFragmento();
                   lista.actualizarLista();
                   banderaEdicion = false;
               }
           }else{
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre",
                        "Hey!", JOptionPane.ERROR_MESSAGE);
           }
       }
      
       
       
   }
   
   
   private void agregarRecurso(){
       AgregarRecurso nuevoRecurso = new AgregarRecurso(codigo_respuesta);
       
   }

    @Override
    public void onItemSelected(String tutUrl) {
       Desktop d = Desktop.getDesktop();
        try {
            d.browse(new URI(tutUrl));
        } catch (URISyntaxException ex) {
            Logger.getLogger(ListaRecursos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListaRecursos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public void onActivityResult(int requestCode, int resultCode, String[] data){
        controladorArbol = ControladorArbolReferencias.obtenerInstancia();
        if(requestCode == codigo_respuesta){
            if (resultCode == CODIGO_AGREGAR_RECURSO){
                 String[] parametrosNodo = data;
                
                 controladorArbol.agregarRecursoAlNodoActual(parametrosNodo[0], false, parametrosNodo[1]);
                 addObject(parametrosNodo[0]);
                ListaRecursosFragmento lista = new ListaRecursosFragmento();
                  lista.actualizarLista();
                  banderaEdicion = true;
            }
        }
    }
    
    
     public DefaultMutableTreeNode addObject(Object child) {
        
          DefaultMutableTreeNode parentNode = null;
           TreePath parentPath = arbol.getSelectionPath();
           
            if (parentPath == null) {
            parentNode = rootNode;
        } else {
            parentNode = (DefaultMutableTreeNode)
                         (parentPath.getLastPathComponent());
        }
            if(parentNode != rootNode){
              
              NodoArbolReferencias nodoTemporal =  NodoArbolReferencias.class.cast(parentNode.getUserObject());
              if(nodoTemporal.obtenerTipo() == false){
                   JOptionPane.showMessageDialog(null, "No puedes agregar elementos a este recurso");
                return null;
              }
            }

        return addObject(parentNode, child);
     }
     
     
      public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) {
           List<NodoArbolReferencias> listaNodos;
          boolean shouldBeVisible = true;
          NodoArbolReferencias nodoTemporal;
          
          listaNodos = controladorArbol.obtenerHijosNodoActual();
          nodoTemporal = listaNodos.get(listaNodos.size()-1);
          
          
           DefaultMutableTreeNode childNode = 
                new DefaultMutableTreeNode(child);
           childNode.setUserObject(nodoTemporal);
        if (parent == null) {
            parent = rootNode;
        }
	
	//It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
        treeModel.insertNodeInto(childNode, parent, 
                                 parent.getChildCount());

        //Make sure the user can see the lovely new node.
        if (shouldBeVisible) {
            arbol.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
      }
    
    private void retroceder() {
        System.out.println("retroceder");
        if (controladorArbol.obtenerNodoActual().equals(controladorArbol.obtenerNodoRaiz())) {
            JOptionPane.showMessageDialog(null, "No puedes retroceder más");
        } else {
            controladorArbol.regresarNodoAnterior();
            ListaRecursosFragmento listaRecursosFragmento = new ListaRecursosFragmento();
            listaRecursosFragmento.cambiarLista();
            

        }
  
    }
    
    public void crearArbolRecursoExistente(){
        List<NodoArbolReferencias> listaNodos;
        NodoArbolReferencias nodoTemporal;
        arbol = lista_fragmento.getArbolObjetos();
        rootNode = lista_fragmento.getRootNode();
        rootNode.setUserObject(controladorArbol.obtenerNodoActual());
        treeModel = lista_fragmento.getTreeModel();
        treeModel.setRoot(rootNode);
        arbol.setModel(treeModel);
        
        
    }
    
    
    
}
