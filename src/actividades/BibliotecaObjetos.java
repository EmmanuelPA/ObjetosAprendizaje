/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividades;


import controladores.ControladorArbolReferencias;
import controladores.GeneradorManifiesto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import vistas.biblioteca_objetos;
import vistas.biblioteca_objetos2;
import vistas.lista_fragmento;

/**
 *
 * @author Estrella
 */
public class BibliotecaObjetos  {
    private List<String> listaObjetos;
    private biblioteca_objetos2 biblioteca_objetos = new biblioteca_objetos2();
    private lista_fragmento lista_fragmento; //= new lista_fragmento();
    private JPanel panel = new JPanel();
    private JMenuBar menuBiblioteca = new JMenuBar();
    private String nombreObjetoSeleccionado;
     ListaRecursos listaRecursos;
    
    
    public BibliotecaObjetos(){
        iniciarComponentes();
    }
    
    
    public void iniciarComponentes(){
         /*menuBiblioteca = biblioteca_objetos.getBarraMenu();
         JButton agregarObjeto = new JButton();
         agregarObjeto.setFocusable(false);
         menuBiblioteca.add(agregarObjeto);
         llenarObjetos();
         llenarVistaObjetos();
         //biblioteca_objetos.getContentPane().add(agregarObjeto);
         
          agregarObjeto.setBorder(null);
          agregarObjeto.setBorderPainted(false);
           agregarObjeto.setContentAreaFilled(false);
            agregarObjeto.setFocusPainted(false);
         ImageIcon img = new ImageIcon("src/botones/ic_action_addnew.png");
         ImageIcon img2 = new ImageIcon("src/botones/ic_action_addnew.png");
         ImageIcon img3 = new ImageIcon("src/botones/ic_action_addnew.png");
         agregarObjeto.setIcon(img);
         agregarObjeto.setPressedIcon(img2);
         agregarObjeto.setRolloverIcon(img3);*/
         
         JButton agregarObjeto = new JButton();
         agregarObjeto = biblioteca_objetos.getAgregarObjeto();
         agregarObjeto.setFocusable(false);
         llenarObjetos();
         llenarVistaObjetos();
         
         agregarObjeto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(null, 
                                                          "Nuevo recurso", 
                                                          "Introduce un nombre para el recurso", 
                                                          JOptionPane.QUESTION_MESSAGE);
                
               if(name == null){
                   return;
               }else if((name.length() > 0)){
                     listaRecursos = listaRecursos.obtenerInstancia();
                        listaRecursos.setnombreObjeto(name);
                        listaRecursos.iniciarComponentes();
                        //System.out.println(name +"Aqui");
                     biblioteca_objetos.dispose();
               }else if((name.length() == 0)){
                   JOptionPane.showMessageDialog(null, "Debe ingresar un nombre",
                        "Hey!", JOptionPane.ERROR_MESSAGE);
               }
            }
        });
         
         //GridLayout layout = new GridLayout(0,2);
         
        // biblioteca_objetos.getContentPane().setLayout(layout);
        // biblioteca_objetos.getPanelBiblioteca().setSize(400,300);
         
         biblioteca_objetos.getScrollBiblioteca().setVerticalScrollBarPolicy(22);
         //biblioteca_objetos.getScrollBiblioteca().setSize(400,300);
         
         biblioteca_objetos.setLocationRelativeTo(null);
         biblioteca_objetos.setVisible(true);
         //biblioteca_objetos.setSize(500, 400);
         biblioteca_objetos.setTitle("LOTUS");
        // biblioteca_objetos.pack();
         biblioteca_objetos.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         
         
    }
    
    private void llenarObjetos(){
        listaObjetos = new ArrayList();
        File biblioteca = new File (GeneradorManifiesto.BIBLIOTECA_CARPETA, "BibliotecaObjetosAprendizaje");
        if (biblioteca.exists()){
            File directorio_biblioteca = new File (GeneradorManifiesto.BIBLIOTECA_CARPETA + "/BibliotecaObjetosAprendizaje");
            File[] listaObjetosGuardados = directorio_biblioteca.listFiles();
            
            if (listaObjetosGuardados.length > 0) {
                for (File infile : listaObjetosGuardados) {
                    if ( infile.isDirectory() ){
                        listaObjetos.add(infile.getName());
                    }
                }
            }
        }
    }
    
    private void llenarVistaObjetos(){
        int i = 0;
        for (String nombreObjeto : listaObjetos){
            JButton buttonObjeto = new JButton();
            buttonObjeto.setBounds(100, 100, 80, 80);
            buttonObjeto.setFocusable(false);
            buttonObjeto.setBackground(Color.white);
            biblioteca_objetos.getPanelBiblioteca().add(buttonObjeto);
            
            ImageIcon img = new ImageIcon("src/botones/marca_agua_lotus.png");
            buttonObjeto.setIcon(img);
            buttonObjeto.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            buttonObjeto.setHorizontalAlignment(SwingConstants.RIGHT);
            buttonObjeto.setHorizontalTextPosition(JButton.LEFT);
            buttonObjeto.setText("<html><div align=left width=80px>" + nombreObjeto + "</div></html>");
            
            final int buttonIndex = i;
            buttonObjeto.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println( buttonIndex + " pressed" );
                    
                    nombreObjetoSeleccionado = listaObjetos.get(buttonIndex);
                    System.out.println(nombreObjetoSeleccionado);
                    listaRecursos = listaRecursos.obtenerInstancia();
                     listaRecursos.setnombreObjeto(nombreObjetoSeleccionado);
                     listaRecursos.setEdicionObjeto("Edicion");
                    listaRecursos.iniciarComponentes();
                    biblioteca_objetos.dispose();
                }
            });
            i++;
        }
    }
    
    private void agregarNuevoObjeto(String nombreObjeto){
       
        GeneradorManifiesto generador = new GeneradorManifiesto();
        ControladorArbolReferencias controlador = ControladorArbolReferencias.obtenerInstancia();
        
        controlador.agregarEstructuraAlNodoActual(nombreObjeto, true);
        controlador.establecerNodoActual(0);
        
        generador.generarXML();
        generador.generarArchivoArbolSerializable();
       
    }
    
    private void reload(){
        biblioteca_objetos.dispose();
        new BibliotecaObjetos();
        //new biblioteca_objetos().setVisible(true);
    }
    
    private void actionPerformed(ActionEvent e){
        
    }
    
   
    
   
    
}
