package GUI.GUI_controller;
import javax.swing.JButton;
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
    
    private final String route;
    private JProgressBar jpbar;
    private JTextArea txtarea; 
    private final JButton nextBtn;

    @Override
    protected Integer doInBackground() throws Exception {
        getJpbar().setIndeterminate(true);
        nextBtn.setEnabled(false);
        
        GUIInstallerController controller = new GUIInstallerController(this.route);
        
        getTxtarea().append("Starting configuration , please wait ... \n");
        controller.getConfiguracion().loadConfigurationFile();
        
        getTxtarea().append("Installing Solver, please wait...\n");
        controller.installSolver();
        
        getTxtarea().append("Setting the environment variables of the solver SWI-Prolog...\n");
        controller.getSolverConfigurations().configureSwiPlEnvironmentVariable(controller.getConfiguracion().operativeSystem);
        
        getTxtarea().append("Setting the environment variables of Java...\n");
        //controller.getJavaConfiguratios().configureJavaHomeEnvironmentVariable(route, );
        
        getTxtarea().append("Downloading VariaMos...\n");
        controller.downloadVariaMos();
        getTxtarea().append("Installing VariaMos...\n");
        
        getTxtarea().append("Creating the Launcher of VariaMos...\n");
        controller.getVariaMosConfiguratios().launchVariamos(controller.getConfiguracion().operativeSystem, this.route);
        getTxtarea().append("VariaMos has been downloaded and installed successfully!! \n");
        
        getJpbar().setIndeterminate(false);
        nextBtn.setEnabled(true);
        return 0;
    }

    public ProgressB(JProgressBar jpbar, String route, JTextArea txtarea, JButton siguienteBtn) {
        this.jpbar = jpbar;
        this.txtarea = txtarea;
        this.route = route;
        this.nextBtn = siguienteBtn;
    }
}