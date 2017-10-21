/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Chosky
 */
public class Final extends javax.swing.JFrame {
    public Final() {
        initComponents();
        ruteTxt.setEditable(false);
        searchBtn.setEnabled(false);
        
        finishBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    finishBtnActionPerformed(ae);
                } catch (IOException ex) {
                    Logger.getLogger(Final.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        directAccessLauncher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                directAccessLauncherActionPerformed(ae);
            }
        });
        
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                searchBtnActionPerformed(ae);
            }
        });
    }
   
    private void finishBtnActionPerformed(ActionEvent ae) throws IOException {
        variamos.utulity.Installer inst = new variamos.utulity.Installer();
        variamos.utulity.Configuration conf = new variamos.utulity.Configuration();
        if (directAccessLauncher.isSelected()) {
            if (executeVariamos.isSelected()) {
                inst.launchVariamos(conf.operativeSystem, "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\"", true);
            } else {
                inst.launchVariamos(conf.operativeSystem, "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\"", false);
            }
        } else {
            if (executeVariamos.isSelected()) {
                inst.launchVariamos(conf.operativeSystem, ruteTxt.getText(), true);
            } else {
                inst.launchVariamos(conf.operativeSystem, ruteTxt.getText(), false);
            }
        }
        this.dispose();
    }
    
    private void searchBtnActionPerformed(ActionEvent ae){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setVisible(true);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
            System.out.println("getCurrentDirectory(): " 
                +  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " 
                + chooser.getSelectedFile());
        } else {
            System.out.println("No Selection ");
        }
    }
    
    private void directAccessLauncherActionPerformed(ActionEvent ae){
        if(!directAccessLauncher.isSelected()){
            ruteTxt.setEditable(true);
            searchBtn.setEnabled(true);
        } else {
            ruteTxt.setEditable(false);
            searchBtn.setEnabled(false);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        variamosTitle = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        directAccessLauncher = new javax.swing.JCheckBox();
        ruteTxt = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        executeVariamos = new javax.swing.JCheckBox();
        finishBtn = new javax.swing.JButton();

        jLabel2.setText("¿Desea agregar un acceso directo del launcher");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        variamosTitle.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 36)); // NOI18N
        variamosTitle.setText("VariaMos Installer");

        jLabel3.setText("¿Desea agregar un acceso directo del launcher");

        jLabel4.setText("en el escritorio?");

        directAccessLauncher.setText("Si");

        searchBtn.setText("buscar");

        jLabel1.setText("¿Desea ejecutar VariaMos?");

        executeVariamos.setText("Si");

        finishBtn.setText("Finalizar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ruteTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(variamosTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(directAccessLauncher)
                            .addComponent(jLabel1)
                            .addComponent(executeVariamos))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(finishBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(variamosTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(directAccessLauncher, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ruteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(executeVariamos)
                .addGap(18, 18, 18)
                .addComponent(finishBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox directAccessLauncher;
    private javax.swing.JCheckBox executeVariamos;
    private javax.swing.JButton finishBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField ruteTxt;
    private javax.swing.JButton searchBtn;
    private javax.swing.JLabel variamosTitle;
    // End of variables declaration//GEN-END:variables
}
