/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;
import controller.GUIInstallerController;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 *
 * @author Hassler
 */
public class ProgressB extends SwingWorker<Integer, String> {

    /**
     * @return the label
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(JLabel label) {
        this.label = label;
    }

    /**
     * @return the jpbar
     */
    public JProgressBar getJpbar() {
        return jpbar;
    }

    /**
     * @param jpbar the jpbar to set
     */
    public void setJpbar(JProgressBar jpbar) {
        this.jpbar = jpbar;
    }

    /**
     * @return the txtarea
     */
    public JTextArea getTxtarea() {
        return txtarea;
    }

    /**
     * @param txtarea the txtarea to set
     */
    public void setTxtarea(JTextArea txtarea) {
        this.txtarea = txtarea;
    }
    
    private String ruta;
    private JLabel label;
    private JProgressBar jpbar;
    private JTextArea txtarea; 

    @Override
    protected Integer doInBackground() throws Exception {
        getJpbar().setIndeterminate(true);
        
        GUIInstallerController controlador = new GUIInstallerController(this.ruta);
        
        
        controlador.getConfiguracion().loadConfigurationFile();
        System.out.println("20");
        getTxtarea().append("Iniciando configuración , por favor espere ... \n");
        
        
        controlador.installSolver();
        System.out.println("40");   
        getTxtarea().append("Instalando solver, por favor espere\n");
        
        
        controlador.getInstalador().configureEnvironmentVariables(controlador.getConfiguracion().operativeSystem);
        getTxtarea().append("Configurando variables de ambiete...\n");
        //barraProgreso.setValue(60);
        System.out.println("60");
        
        controlador.downloadVariaMos();
        //barraProgreso.setValue(80);
        System.out.println("80");
        getTxtarea().append("Instalando VariaMos ...\n");
        
        controlador.getInstalador().launchVariamos(controlador.getConfiguracion().operativeSystem, this.ruta);
        //barraProgreso.setValue(100);
        getTxtarea().append("El proceso de instalación ha finalizado exitosamente ...\n");
        
        //siguienteBtn.setEnabled(true); **/
        
        getJpbar().setIndeterminate(false);
        return 0;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProgressB(JProgressBar jpbar, String ruta, JTextArea txtarea) {
        this.label = label;
        this.jpbar = jpbar;
        this.txtarea = txtarea;
        this.ruta = ruta;
    }
    
    
}
