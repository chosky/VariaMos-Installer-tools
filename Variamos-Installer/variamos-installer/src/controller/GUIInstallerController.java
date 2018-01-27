package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import variamos.utility.Configuration;
import variamos.utility.Installer;
import GUI.*;

/**
 *
 * @author José David Henao Ocampo - Monitor Especialización en Desarrollo de Software
 *    &    Hassler Castro Cuesta - Monitor Especialización en Diseño Mecánico
 */
public class GUIInstallerController {
    
    private Configuration configuracion;
    private variamos.utility.Installer instalador;
    private String rutaVariaMos;
    
    public GUIInstallerController(String rutaVariaMos) {
        configuracion = new Configuration();
        instalador = new variamos.utility.Installer();
        this.rutaVariaMos = rutaVariaMos;
    }
    
    public void installSolver() throws FileNotFoundException, IOException, InterruptedException{
        Object[] options = {"Reintentar", "Cancelar"};
        int opcion;
        if(configuracion.solverDl != null){
            while(!instalador.downloadSolverFromURL(configuracion.solverDl, configuracion.solverName)) {
                opcion = JOptionPane.showOptionDialog(null, "No se pudo hacer conexion para descargar el solver.", "PELIGRO!!", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if(opcion == 0) continue;
                if(opcion == 1) return;
            }
            JOptionPane.showMessageDialog(null, "Solver descargado correctamente.");
        }
        if(!downloadSolverTerminal())
            JOptionPane.showMessageDialog(null, "No se instalo el solver. \n Procedo a descargar variamos");
    }
    
    public boolean downloadSolverTerminal() throws IOException, InterruptedException{
        String sudoPass = "";       
        int option;
        Object[] options = {"REINTENTAR", "CANCELAR"};
        if(configuracion.operativeSystem.contains("Linux") || configuracion.operativeSystem.contains("Mac OS")){
            sudoPass = requestPassword();
        }
        while(!instalador.installSolver(configuracion.operativeSystem, configuracion.solverName, sudoPass)){
            option = JOptionPane.showOptionDialog(null, "Contraseña de sudo erronea", "PELIGRO!!", 
                                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if(option == 0){
                sudoPass = requestPassword();
            } else {
                return false;
            }
        }
        return true;
    }
    
    public String requestPassword(){
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
    
    public void downloadVariaMos() throws FileNotFoundException, IOException, MalformedURLException, InterruptedException{
        Object[] options = {"REINTENTAR", "CANCELAR"};
        Object[] optionsWithManualDownload = {"REINTENTAR", "CANCELAR", "DAR RUTA"};
        int option;
        int cont = 0;
        while(!instalador.downloadVariamosFromURL(configuracion.variamosDl, configuracion.variamosVersion, rutaVariaMos)) {
            if(cont >= 3){
                option = JOptionPane.showOptionDialog(null, "No se pudo hacer conexion para descargar VariaMos.", "PELIGRO!!", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsWithManualDownload, optionsWithManualDownload[0]);
            } else {
                option = JOptionPane.showOptionDialog(null, "No se pudo hacer conexion para descargar VariaMos.", "PELIGRO!!", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }
            if(option == 0){
                ++cont;
                continue;
            } else if(option == 2) {
                 configuracion.solverDl = JOptionPane.showInputDialog("Dame la ruta de VariaMos");
            }
            else break;
        }
        JOptionPane.showMessageDialog(null, "VariaMos descargado correctamente.");
    }
    
    public Configuration getConfiguracion() {
        return configuracion;
    }

    public Installer getInstalador() {
        return instalador;
    }
    
}