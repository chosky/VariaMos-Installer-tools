package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José David Henao Ocampo - Monitor EDS
 */
public class Final extends javax.swing.JFrame {
    
    String variamosRute;
    public Final(String variamosRute) {
        initComponents();
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
        variamos.utility.Installer inst = new variamos.utility.Installer();
        variamos.utility.Configuration conf = new variamos.utility.Configuration();
        
        if (directAccessLauncher.isSelected()) {
            if (executeVariamos.isSelected()) {
                if(conf.operativeSystem.contains("Windows")) {
                    inst.launchVariamos(conf.operativeSystem, "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop", variamosRute, true);
                } else if (conf.operativeSystem.contains("Linux") || conf.operativeSystem.contains("Mac OS")) {
                    generateLauncher(inst, conf, true);
                }
            } else {
                if(conf.operativeSystem.contains("Windows")) {
                    inst.launchVariamos(conf.operativeSystem, "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop", variamosRute, false);
                } else if (conf.operativeSystem.contains("Linux") || conf.operativeSystem.contains("Mac OS")) {
                    generateLauncher(inst, conf, false);
                }
            }
            inst.launchVariamos(conf.operativeSystem, variamosRute, variamosRute, false);
        } else {
            if (executeVariamos.isSelected()) {
                inst.launchVariamos(conf.operativeSystem, variamosRute, variamosRute, true);
            } else {
                inst.launchVariamos(conf.operativeSystem, variamosRute, variamosRute, false);
            }
        }
        this.dispose();
    }
    
    private void generateLauncher(variamos.utility.Installer inst, variamos.utility.Configuration conf, boolean executeVariamos) throws IOException {
        String idioma = Locale.getDefault().getLanguage();
        String descarga = selectorIdioma(idioma);
        inst.launchVariamos(conf.operativeSystem, "/home/" + System.getProperty("user.name") + "/" + descarga, variamosRute, executeVariamos);
    }
    
    private String selectorIdioma(String idioma) {
        switch(idioma) {
            case "es":
                System.out.println("Español");
                return "Escritorio";
            case "en":
                System.out.println("Ingles");
                return "Desktop";
            case "fr":
                System.out.println("Frances");
                return "Bureau";
        }
        return "Escritorio";
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        variamosTitle = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        directAccessLauncher = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        executeVariamos = new javax.swing.JCheckBox();
        finishBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        jLabel2.setText("¿Desea agregar un acceso directo del launcher");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        variamosTitle.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 36)); // NOI18N
        variamosTitle.setText("VariaMos Installer");

        jLabel3.setText("¿Desea agregar un acceso directo del launcheren el escritorio?");

        directAccessLauncher.setText("Si");

        jLabel1.setText("¿Desea ejecutar VariaMos?");

        executeVariamos.setText("Si");

        finishBtn.setText("Finalizar");

        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Si no lo selecciona, el launcher estará donde descargó VariaMos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(finishBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(directAccessLauncher)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator1)
                                .addComponent(variamosTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(executeVariamos)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addComponent(directAccessLauncher, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(finishBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(executeVariamos)))
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel variamosTitle;
    // End of variables declaration//GEN-END:variables
}
