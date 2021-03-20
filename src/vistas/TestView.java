/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

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

/**
 *
 * @author cherrera
 */
public class TestView extends javax.swing.JFrame {

    /**
     * Creates new form TestView
     */
    
    private List<String> listaObjetos;
    
    public TestView() {
        initComponents();
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        GridLayout layout = new GridLayout(0,2);
        getContentPane().setLayout(layout);
        
        llenarObjetos();
        
        llenarVistaObjetos();
        
        JButton agregarObjeto = new JButton("+");
        agregarObjeto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(null, 
                                                          "Nuevo recurso", 
                                                          "Introduce un nombre para el recurso", 
                                                          JOptionPane.QUESTION_MESSAGE);
                System.out.println(name);
                agregarNuevoObjeto(name);
                
                reload();
            }
        });
        
        getContentPane().add(agregarObjeto);

        pack();
    }// </editor-fold>
    
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
            JButton buttonObjeto = new JButton(nombreObjeto);
            getContentPane().add(buttonObjeto);
            
            final int buttonIndex = i;
            buttonObjeto.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println( buttonIndex + " pressed" );
                    
                    
                    
                }
            });
            i++;
        }
    }
    
    
    private void reload(){
        this.dispose();
        new TestView().setVisible(true);
    }
    
    private void agregarNuevoObjeto(String nombreObjeto){
        GeneradorManifiesto generador = new GeneradorManifiesto();
        ControladorArbolReferencias controlador = ControladorArbolReferencias.obtenerInstancia();
        
        controlador.agregarEstructuraAlNodoActual(nombreObjeto, true);
        controlador.establecerNodoActual(0);
        
        generador.generarXML();
        generador.generarArchivoArbolSerializable();
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
