package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author José David Henao Ocampo - Monitor Especialización en Desarrollo de Software
 *    &    Hassler Castro Cuesta - Monitor Especialización en Diseño Mecánico
 */
public class Inicio extends javax.swing.JFrame {

    public Inicio() {
        initComponents();
        infoLbl.setEditable(false);
        this.setTitle("Instalador de VariaMos");
        
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cancelBtnActionPerformed(ae);
            }
        });
        
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                nextBtnActionPerformed(ae);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        variamosTitle = new javax.swing.JLabel();
        nextBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoLbl = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        variamosTitle.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 36)); // NOI18N
        variamosTitle.setText("Instalador de VariaMos");

        nextBtn.setText("siguiente");

        cancelBtn.setText("cancelar");

        infoLbl.setColumns(20);
        infoLbl.setRows(5);
        infoLbl.setText("Herramienta para la instalación de VariaMos \ncon todas sus dependencias, instalación de \nSWI-Prolog y configuración de sus variables \nde ambiente.\nLa herramienta es funcional para distintos \nsistemas operativos como Windows, \nLinux (distribuciones de Debian) e IOs.\n\nDesarrollada por el monitor de EDS.\nMedellín-EAFIT-2017");
        jScrollPane1.setViewportView(infoLbl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(variamosTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(variamosTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextBtn)
                    .addComponent(cancelBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextBtnActionPerformed(ActionEvent ae){
        Installer installer = new Installer();
        installer.setVisible(true);
        this.dispose();
    }
    
    private void cancelBtnActionPerformed(ActionEvent ae){
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextArea infoLbl;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nextBtn;
    private javax.swing.JLabel variamosTitle;
    // End of variables declaration//GEN-END:variables
}
