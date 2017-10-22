package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author José David Henao Ocampo - Monitor EDS
 */
public class Installer extends javax.swing.JFrame {
    
    public Installer(){
        initComponents();
        this.setTitle("Instalador de VariaMos y SWI-Prolog");
        nextBtn.setEnabled(false);
        installBtn.setEnabled(false);
        printTxt.setEditable(false);
        
        //printTxt.setText("\n");
        //printTxt.setText("\n");
        
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                nextBtnActionPerformed(ae);
            }
        });
        
        aproveChBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                aproveChBtnActionPerformed(ae);
            }
        });
        
        installBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                installBtnActionPerformed(ae);
            }
        });
        
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cancelBtnActionPerformed(ae);
            }
        });
        
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                backBtnActionPerformed(ae);
            }
        });
        
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                searchBtnActionPerformed(ae);
            }
        });
        
    }
    
    private void aproveChBtnActionPerformed(ActionEvent ae){
        if(aproveChBtn.isSelected()){
            installBtn.setEnabled(true);
        } else {
            installBtn.setEnabled(false);
        }
    }
    
    private void nextBtnActionPerformed(ActionEvent ae) {
        Final fin = new Final(variamosRuteTxt.getText());
        fin.setVisible(true);
        this.dispose();
   }
    
    private void installBtnActionPerformed(ActionEvent ae){
        variamos.utulity.Executor exe = new  variamos.utulity.Executor();
        if (variamosRuteTxt.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "¡Ingresa una ruta!");
        } else {
            File file = new File(variamosRuteTxt.getText());
            if (file.exists()){
                downloadLbl.setForeground(Color.red);
                exe.Executor(variamosRuteTxt.getText());
                exe.executorCode();
            } else {
                JOptionPane.showMessageDialog(null, "¡El directorio no existe!");
            }
            downloadLbl.setForeground(Color.green);
            downloadLbl.setText("Descarga y configuración exitosa!!");
            nextBtn.setEnabled(true);
            installBtn.setEnabled(false);
        }
    }
    
    private void searchBtnActionPerformed(ActionEvent ae){
        JFileChooser dirSelector = new JFileChooser();
        dirSelector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        dirSelector.showOpenDialog(this);
        File directory = dirSelector.getSelectedFile();
        String directoryStr = directory.toString();
        variamosRuteTxt.setText(directoryStr);
    }
    
    private void backBtnActionPerformed(ActionEvent ae){
        this.dispose();
        Inicio ini = new Inicio();
        
        ini.setVisible(true);
    }
    
    private void cancelBtnActionPerformed(ActionEvent ae){
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        variamosTitle = new javax.swing.JLabel();
        nextBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        installBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        printTxt = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        aproveChBtn = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        variamosRuteTxt = new javax.swing.JTextField();
        downloadLbl = new javax.swing.JLabel();
        searchBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        variamosTitle.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 36)); // NOI18N
        variamosTitle.setText("VariaMos Installer");

        nextBtn.setText("siguiente");

        backBtn.setText("atras");

        cancelBtn.setText("cancelar");

        installBtn.setText("instalar");

        jLabel1.setText("Se instalarán los siguientes paquetes: ");

        printTxt.setColumns(20);
        printTxt.setRows(5);
        printTxt.setText("SWI-Prolog 7.2.3\nVariamos 1.0.1.20");
        jScrollPane1.setViewportView(printTxt);

        jLabel2.setText("¿Está deacuerdo con instalar todo?");

        aproveChBtn.setText("Sí");

        jLabel3.setText("Dame la ruta donde deseas VariaMos :");

        downloadLbl.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        downloadLbl.setForeground(new java.awt.Color(204, 204, 204));
        downloadLbl.setText("Descargando y configurando...");

        searchBtn.setText("Buscar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 174, Short.MAX_VALUE)
                        .addComponent(cancelBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aproveChBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(installBtn))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(downloadLbl)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(variamosRuteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchBtn)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(variamosTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(variamosTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(variamosRuteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn))
                .addGap(18, 18, 18)
                .addComponent(downloadLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(installBtn)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(aproveChBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextBtn)
                    .addComponent(backBtn)
                    .addComponent(cancelBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox aproveChBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel downloadLbl;
    private javax.swing.JButton installBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nextBtn;
    public javax.swing.JTextArea printTxt;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField variamosRuteTxt;
    private javax.swing.JLabel variamosTitle;
    // End of variables declaration//GEN-END:variables
}
