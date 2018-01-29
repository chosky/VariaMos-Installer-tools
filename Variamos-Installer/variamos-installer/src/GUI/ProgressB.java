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
        
        
        controlador.downloadVariaMos();
        System.out.println("80");
        getTxtarea().append("Instalando VariaMos ...\n");
        
        controlador.getInstalador().launchVariamos(controlador.getConfiguracion().operativeSystem, this.ruta);
        getTxtarea().append("El proceso de instalación ha finalizado exitosamente ...\n");
        
        
        getJpbar().setIndeterminate(false);
        return 0;
    }

    public ProgressB(JProgressBar jpbar, String ruta, JTextArea txtarea) {
        this.jpbar = jpbar;
        this.txtarea = txtarea;
        this.ruta = ruta;
    }
    
    
}
