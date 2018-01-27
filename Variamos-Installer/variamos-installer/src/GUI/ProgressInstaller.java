package GUI;

import controller.GUIInstallerController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author José David Henao Ocampo - Monitor Especialización en Desarrollo de Software
 *    &    Hassler Castro Cuesta - Monitor Especialización en Diseño Mecánico
 */
public class ProgressInstaller extends javax.swing.JFrame {

    public ProgressInstaller(String rutaVariamos) {
        initComponents();
        this.setTitle("Instalando VariaMos y SWI-Prolog...");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        siguienteBtn.setEnabled(false);
        pasosTerminalTxt.setEditable(false);
        
        try {
            instalacionProgramas(rutaVariamos);
        } catch (SAXException ex) {
            Logger.getLogger(ProgressInstaller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProgressInstaller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ProgressInstaller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProgressInstaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        siguienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                siguienteBtnActionPerformed(ae, rutaVariamos);
            }
        }); 
        
    }
    
    private void instalacionProgramas(String rutaVariaMos) throws SAXException, IOException, ParserConfigurationException, FileNotFoundException, InterruptedException {
        GUIInstallerController controlador = new GUIInstallerController(rutaVariaMos);
        
        controlador.getConfiguracion().loadConfigurationFile();
        barraProgreso.setValue(20);
        
        controlador.installSolver();
        barraProgreso.setValue(40);
            
        controlador.getInstalador().configureEnvironmentVariables(controlador.getConfiguracion().operativeSystem);
        barraProgreso.setValue(60);
        
        controlador.downloadVariaMos();
        barraProgreso.setValue(80);
        
        controlador.getInstalador().launchVariamos(controlador.getConfiguracion().operativeSystem, rutaVariaMos);
        barraProgreso.setValue(100);
        
        siguienteBtn.setEnabled(true); 
    }
    
    private void siguienteBtnActionPerformed(ActionEvent ae, String rutaVariamos) {
        Final vistaFinal = new Final(rutaVariamos);
        vistaFinal.setVisible(true);
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        variamosTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        barraProgreso = new javax.swing.JProgressBar();
        siguienteBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pasosTerminalTxt = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        variamosTitle.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        variamosTitle.setText("Instando VariaMos y SWI-Prolog");

        siguienteBtn.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        siguienteBtn.setText("Siguiente");

        pasosTerminalTxt.setColumns(20);
        pasosTerminalTxt.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        pasosTerminalTxt.setRows(5);
        jScrollPane1.setViewportView(pasosTerminalTxt);

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
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(siguienteBtn)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(variamosTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(siguienteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTextArea pasosTerminalTxt;
    private javax.swing.JButton siguienteBtn;
    private javax.swing.JLabel variamosTitle;
    // End of variables declaration//GEN-END:variables
}
