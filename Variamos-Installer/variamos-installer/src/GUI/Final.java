package GUI;

import controller.GUIFinalController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José David Henao Ocampo - Monitor Especialización en Desarrollo de Software
 *    &    Hassler Castro Cuesta - Monitor Especialización en Diseño Mecánico
 */
public class Final extends javax.swing.JFrame {
    
    String variamosRute;
    public Final(String variamosRute) {
        initComponents();
        this.setTitle("Instalador de VariaMos");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.variamosRute = variamosRute;
        directAccessLauncher.setSelected(true);
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
        
    }
   
    private void finishBtnActionPerformed(ActionEvent ae) throws IOException {
        GUIFinalController finalControler = new GUIFinalController();
        if (directAccessLauncher.isSelected()) {
            if (executeVariamos.isSelected()) {
                finalControler.opcionesDeLauncherYFinal(true, true, System.getProperty("os.name"), variamosRute);
            } else {
                finalControler.opcionesDeLauncherYFinal(true, false, System.getProperty("os.name"), variamosRute);
            }
        } else {
            if (executeVariamos.isSelected()) {
                finalControler.opcionesDeLauncherYFinal(false, true, System.getProperty("os.name"), variamosRute);
            } else {
                finalControler.opcionesDeLauncherYFinal(false, false, System.getProperty("os.name"), variamosRute);
            }
        }
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        directAccessLauncher = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        executeVariamos = new javax.swing.JCheckBox();
        finishBtn = new javax.swing.JButton();
        variamosTitle = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        jLabel2.setText("¿Desea agregar un acceso directo del launcher");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setText("¿Desea agregar un acceso directo del launcher en el escritorio?");

        directAccessLauncher.setText("Si");

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setText("¿Desea ejecutar VariaMos?");

        executeVariamos.setText("Si");

        finishBtn.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        finishBtn.setText("Finalizar");

        variamosTitle.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        variamosTitle.setText("Instalador de VariaMos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(variamosTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(directAccessLauncher)
                                    .addComponent(finishBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(executeVariamos)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(variamosTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(directAccessLauncher, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(executeVariamos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addComponent(finishBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel variamosTitle;
    // End of variables declaration//GEN-END:variables
}
