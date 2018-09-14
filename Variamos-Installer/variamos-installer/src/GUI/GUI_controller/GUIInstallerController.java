package GUI.GUI_controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import variamos.utility.Configuration;
import variamos.utility.Installer;

/**
 *
 * @author José David Henao Ocampo - Monitor Especialización en Desarrollo de Software
 *    &    Hassler Castro Cuesta - Monitor Especialización en Diseño Mecánico
 */
public class GUIInstallerController {
    
    private Configuration configuration;
    private variamos.utility.Installer installer;
    private final String variamosRoute;
    private final Object[] acept = {"OK"};
    
    public GUIInstallerController(String variamosRoute) {
        configuration = new Configuration();
        installer = new variamos.utility.Installer();
        this.variamosRoute = variamosRoute;
    }
    
    public void installSolver() throws FileNotFoundException, IOException, InterruptedException{
        Object[] options = {"Try again", "Cancel"};
    
        int option;
        if(configuration.solverDl != null){
            while(!installer.downloadSolverFromURL(configuration.solverDl, configuration.solverName)) {
                option = JOptionPane.showOptionDialog(null, "Solver could not been downloaded", "Warning!!", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if(option == 0) continue;
                if(option == 1) System.exit(1);
            }
            JOptionPane.showOptionDialog(null, "Solver has been downloaded successfully ", "Message", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, acept, acept[0]);
        }
        if(!downloadSolverTerminal())
            JOptionPane.showOptionDialog(null, "Solver has been downloaded successfully ", "Message", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, acept, acept[0]);
    }
    
    public boolean downloadSolverTerminal() throws IOException, InterruptedException{
        String sudoPass = "";       
        int option;
        Object[] options = {"Try again", "Cancel"};
        if(configuration.operativeSystem.contains("Linux") || configuration.operativeSystem.contains("Mac OS")){
            sudoPass = requestPassword();
        }
        while(!installer.instalarSolver(configuration.operativeSystem, configuration.solverName, sudoPass)){
            option = JOptionPane.showOptionDialog(null, "Invalid password", "Warning!!", 
                                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
            if(option == 0)  sudoPass = requestPassword();
            else return false;
        }
        return true;
    }
    
    public String requestPassword(){
        char[] password;
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Sudo password: ");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"Ok", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, panel, "Sudo password",
                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                         null, options, options[0]);
        if(option == 0) {
            password = pass.getPassword();
            return new String(password);
        }else{
            return "";
        }
    }
    
    public void downloadVariaMos() throws FileNotFoundException, IOException, MalformedURLException, InterruptedException{
        Object[] options = {"Try again", "Cancel"};
        int option;
        File variamosFile;
        if(configuration.operativeSystem.equals("Windows")) variamosFile = new File(variamosRoute + "\\Variamos-Resources");
        else variamosFile = new File(variamosRoute + "/Variamos-Resources");
        variamosFile.mkdir();
        while(!installer.downloadVariamosFromURL(configuration.variamosDl, configuration.variamosVersion, variamosRoute)) {
                option = JOptionPane.showOptionDialog(null, "VariaMos could not been downloaded", "Warning!!", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if(option == 0) continue;
                if(option == 1) System.exit(1);
        }
        JOptionPane.showOptionDialog(null, "VariaMos has been downloaded successfully ", "Message", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, acept, acept[0]);
    }
    
    public Configuration getConfiguracion() {
        return configuration;
    }

    public Installer getInstalador() {
        return installer;
    }
    
}