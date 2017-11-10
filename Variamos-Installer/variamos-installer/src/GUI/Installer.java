package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import variamos.utulity.Configuration;

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
                try {
                    installBtnActionPerformed(ae);
                } catch (SAXException ex) {
                    Logger.getLogger(Installer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Installer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(Installer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Installer.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            if(variamosRuteTxt.getText().equals("") || variamosRuteTxt.getText().equals(" ")) {
                JOptionPane.showMessageDialog(this, "Ingrese una ruta!!");
                installBtn.setEnabled(false);
            } else { 
                installBtn.setEnabled(true);
            }
        } else {
            installBtn.setEnabled(false);
        }
    }
    
    private void nextBtnActionPerformed(ActionEvent ae) {
        Final fin = new Final(variamosRuteTxt.getText());
        fin.setVisible(true);
        this.dispose();
   }
    
    private void installBtnActionPerformed(ActionEvent ae) throws SAXException, IOException, ParserConfigurationException, InterruptedException{
        //Loading installation configuration
        Configuration configuration = new Configuration();
        configuration.loadConfigurationFile();
        variamos.utulity.Installer installer = new variamos.utulity.Installer();
        
        //Installing solver
        installSolver(configuration, installer);
        
        //Configure environment variables
        installer.configureEnvironmentVariables(configuration.operativeSystem);
        
        //Downloading VariaMos 
        downloadVariaMos(configuration, installer);
        
        nextBtn.setEnabled(true);
        installBtn.setEnabled(false);
    }
    
    private void installSolver(Configuration configuration, variamos.utulity.Installer installer) throws FileNotFoundException, IOException, InterruptedException{
        Object[] options = {"REINTENTAR", "CANCELAR"};
        Object[] optionsWithManualDownload = {"REINTENTAR", "CANCELAR", "DAR RUTA"};
        
        int cont = 0;
        int option;

        if(configuration.solverDl != null){
            while(!installer.downloadSolverFromURL(configuration.solverDl, configuration.solverName)) {
                if(cont >= 3){
                    option = JOptionPane.showOptionDialog(this, "No se pudo hacer conexion para descargar el solver.", "PELIGRO!!", 
                                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsWithManualDownload, optionsWithManualDownload[0]);
                } else {
                    option = JOptionPane.showOptionDialog(this, "No se pudo hacer conexion para descargar el solver.", "PELIGRO!!", 
                                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                }
                if(option == 0) {
                    ++cont;
                    continue;
                } else if(option == 2) {
                    configuration.solverDl = JOptionPane.showInputDialog("Dame la ruta del instalador del solver");
                }
                else break;
            }
            JOptionPane.showMessageDialog(this, "Solver descargado correctamente.");
        }
        downloadSolverTerminal(configuration, installer);
    }
    
    private void downloadSolverTerminal(Configuration configuration, variamos.utulity.Installer installer) throws IOException, InterruptedException{
        String sudoPass = "";
        if(configuration.operativeSystem.contains("Linux") || configuration.operativeSystem.contains("Mac OS")){
            sudoPass = requestPassword();
        }
        installer.installSolver(configuration.operativeSystem, configuration.solverName, sudoPass);
    }
    
    private void downloadVariaMos(Configuration configuration, variamos.utulity.Installer installer) throws FileNotFoundException, IOException, MalformedURLException, InterruptedException{
        Object[] options = {"REINTENTAR", "CANCELAR"};
        Object[] optionsWithManualDownload = {"REINTENTAR", "CANCELAR", "DAR RUTA"};
        int option;
        int cont = 0;
        while(!installer.downloadVariamosFromURL(configuration.variamosDl, configuration.version, variamosRuteTxt.getText())) {
            if(cont >= 3){
                option = JOptionPane.showOptionDialog(this, "No se pudo hacer conexion para descargar VariaMos.", "PELIGRO!!", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsWithManualDownload, optionsWithManualDownload[0]);
            } else {
                option = JOptionPane.showOptionDialog(this, "No se pudo hacer conexion para descargar VariaMos.", "PELIGRO!!", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }
            if(option == 0){
                ++cont;
                continue;
            } else if(option == 2) {
                 configuration.solverDl = JOptionPane.showInputDialog("Dame la ruta de VariaMos");
            }
            else break;
        }
        JOptionPane.showMessageDialog(this, "VariaMos descargado correctamente.");
    }
    
    private String requestPassword(){
        char[] passWord;
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Ingresa la contraseña de sudo:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"OK", "CANCELAR"};
        int option = JOptionPane.showOptionDialog(null, panel, "Contraseña de sudo",
                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                         null, options, options[0]);
        if(option == 0) {
            passWord = pass.getPassword();
            return new String(passWord);
        }else{
            return "";
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
        searchBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        variamosTitle.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 36)); // NOI18N
        variamosTitle.setText("VariaMos Installer");

        nextBtn.setText("SIGUIENTE");

        backBtn.setText("ATRAS");

        cancelBtn.setText("CANCELAR");

        installBtn.setText("INSTALAR");

        jLabel1.setText("Se instalarán los siguientes paquetes: ");

        printTxt.setColumns(20);
        printTxt.setRows(5);
        printTxt.setText("SWI-Prolog 7.2.3\nVariamos 1.0.1.20");
        jScrollPane1.setViewportView(printTxt);

        jLabel2.setText("¿Está deacuerdo con instalar todo?");

        aproveChBtn.setText("Sí");

        jLabel3.setText("Dame la ruta donde deseas VariaMos :");

        searchBtn.setText("BUSCAR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(variamosRuteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchBtn)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(variamosTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextBtn)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
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
