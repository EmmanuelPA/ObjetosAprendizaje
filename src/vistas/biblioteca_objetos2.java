/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Estrella
 */
public class biblioteca_objetos2 extends javax.swing.JFrame {

    /**
     * Creates new form biblioteca_objetos2
     */
    public biblioteca_objetos2() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        agregarObjeto = new javax.swing.JButton();
        scrollBiblioteca = new javax.swing.JScrollPane();
        panelBiblioteca = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(780, 522));

        jPanel1.setBackground(new java.awt.Color(51, 181, 229));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/title.png"))); // NOI18N
        jPanel1.add(jLabel1);

        jPanel3.setBackground(new java.awt.Color(51, 181, 229));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);

        agregarObjeto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/ic_action_addnew.png"))); // NOI18N
        agregarObjeto.setBorder(null);
        agregarObjeto.setBorderPainted(false);
        agregarObjeto.setContentAreaFilled(false);
        agregarObjeto.setFocusPainted(false);
        agregarObjeto.setPreferredSize(new java.awt.Dimension(40, 40));
        agregarObjeto.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/ic_action_addnew.png"))); // NOI18N
        agregarObjeto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/botones/ic_action_addnew.png"))); // NOI18N
        agregarObjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarObjetoActionPerformed(evt);
            }
        });
        jPanel1.add(agregarObjeto);

        panelBiblioteca.setLayout(new java.awt.GridLayout(0, 2));
        scrollBiblioteca.setViewportView(panelBiblioteca);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollBiblioteca)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollBiblioteca, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarObjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarObjetoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_agregarObjetoActionPerformed

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
            java.util.logging.Logger.getLogger(biblioteca_objetos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(biblioteca_objetos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(biblioteca_objetos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(biblioteca_objetos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new biblioteca_objetos2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarObjeto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel panelBiblioteca;
    private javax.swing.JScrollPane scrollBiblioteca;
    // End of variables declaration//GEN-END:variables

    public JButton getAgregarObjeto() {
        return agregarObjeto;
    }

    public JPanel getPanelBiblioteca() {
        return panelBiblioteca;
    }

    public JScrollPane getScrollBiblioteca() {
        return scrollBiblioteca;
    }

    

}
