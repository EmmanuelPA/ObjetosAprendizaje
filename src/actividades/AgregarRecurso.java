/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividades;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import vistas.agregar_recurso;
import vistas.agregar_recurso2;
import vistas.lista_fragmento;

/**
 *
 * @author Estrella
 */
public class AgregarRecurso {
    
    int codigo_respuesta = 2;
    int resultCode;
    private JButton botonAgregarRecurso;
    private JButton botonCancelar;
    private boolean edicionRecurso;
    
    private agregar_recurso recurso = new agregar_recurso(); //Vista 1
    agregar_recurso2 recurso2 = new agregar_recurso2(new javax.swing.JFrame(), true);
    private ListaRecursos datos;
    JTextField nombreRecurso = new JTextField();
    JTextField urlRecurso = new JTextField();
    String[] parametrosNuevoNodo=new String[2];//Son los datos del recurso
    
    
    
    public AgregarRecurso(int codigo_respuesta){
        this.codigo_respuesta = codigo_respuesta;
        iniciarComponentes();
    }
    
     public void iniciarComponentes(){
         datos = datos.obtenerInstancia();
         botonAgregarRecurso = recurso2.getGuardar_recurso();
         botonCancelar = recurso2.getCancelar();
         botonAgregarRecurso.setFocusable(false);
         botonCancelar.setFocusable(false);
          edicionRecurso=false;
          
          //Falta la parte de cuando se esta editando el recurso.
          
          botonAgregarRecurso.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent ae) {
                 
                parametrosNuevoNodo = obtenerParametrosNuevoNodo();
                if(edicionRecurso==true){
                    resultCode = 3;
                }else{
                    resultCode = 1;
                    datos.onActivityResult(codigo_respuesta, resultCode, parametrosNuevoNodo);
                }
                recurso2.dispose();
               
             }
          
          
          });
          
          botonCancelar.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent ae) {
                  recurso2.dispose();
                  
             }
          
          });
          
           
         recurso2.setLocationRelativeTo(null);
         recurso2.setVisible(true);
         recurso2.setSize(600, 1000);
         recurso2.setTitle("Agregar Recurso");
        // biblioteca_objetos.pack();
         recurso2.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
          
          
          
     }
     
     
     private String[] obtenerParametrosNuevoNodo(){
        String[] parametrosNuevoNodo=new String[2];
        parametrosNuevoNodo[0]= recurso2.getCampo_texto_nombre().getText().toString();
        parametrosNuevoNodo[1]=recurso2.getCampo_texto_url().getText().toString();
        return parametrosNuevoNodo;

    }
}
