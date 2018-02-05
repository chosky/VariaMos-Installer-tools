package GUI;
import controller.GUIInstallerController;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 *
 * @author José David Henao Ocampo - Monitor Especialización en Desarrollo de Software
 *    &    Hassler Castro Cuesta - Monitor Especialización en Diseño Mecánico
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
    private JButton siguienteBtn;

    @Override
    protected Integer doInBackground() throws Exception {
        getJpbar().setIndeterminate(true);
        siguienteBtn.setEnabled(false);
        
        GUIInstallerController controlador = new GUIInstallerController(this.ruta);
        
        controlador.getConfiguracion().loadConfigurationFile();
        getTxtarea().append("Iniciando configuración , por favor espere ... \n");
        
        controlador.instalarSolver();
        getTxtarea().append("Instalando solver, por favor espere\n");
        
        controlador.getInstalador().configureEnvironmentVariables(controlador.getConfiguracion().sistemaOperativo);
        getTxtarea().append("Configurando variables de ambiete...\n");
        
        controlador.descargarVariaMos();
        getTxtarea().append("Instalando VariaMos ...\n");
        
        controlador.getInstalador().launchVariamos(controlador.getConfiguracion().sistemaOperativo, this.ruta);
        getTxtarea().append("El proceso de instalación ha finalizado exitosamente ...\n");
        
        getJpbar().setIndeterminate(false);
        siguienteBtn.setEnabled(true);
        return 0;
    }

    public ProgressB(JProgressBar jpbar, String ruta, JTextArea txtarea, JButton siguienteBtn) {
        this.jpbar = jpbar;
        this.txtarea = txtarea;
        this.ruta = ruta;
        this.siguienteBtn = siguienteBtn;
    }
}