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

/**
 *
 * @author José David Henao Ocampo - Monitor Especialización en Desarrollo de Software
 *    &    Hassler Castro Cuesta - Monitor Especialización en Diseño Mecánico
 */
public class GUIInstallerController {
    
    private Configuration configuracion;
    private variamos.utility.Installer instalador;
    private String rutaVariaMos;
    private Object[] acept = {"OK"};
    
    public GUIInstallerController(String rutaVariaMos) {
        configuracion = new Configuration();
        instalador = new variamos.utility.Installer();
        this.rutaVariaMos = rutaVariaMos;
    }
    
    public void instalarSolver() throws FileNotFoundException, IOException, InterruptedException{
        Object[] opciones = {"Try again", "Cancel"};
        
    
        int opcion;
        if(configuracion.solverDl != null){
            while(!instalador.downloadSolverFromURL(configuracion.solverDl, configuracion.nombreSolver)) {
                opcion = JOptionPane.showOptionDialog(null, "Solver could not been downloaded", "Warning!!", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if(opcion == 0) continue;
                if(opcion == 1) System.exit(1);
            }
            JOptionPane.showOptionDialog(null, "Solver has been downloaded successfully ", "Message", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, acept, acept[0]);
        }
        if(!descargarSolverTerminal())
            JOptionPane.showOptionDialog(null, "Solver has been downloaded successfully ", "Message", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, acept, acept[0]);
    }
    
    public boolean descargarSolverTerminal() throws IOException, InterruptedException{
        String sudoPass = "";       
        int opcion;
        Object[] opciones = {"Try again", "Cancel"};
        if(configuracion.sistemaOperativo.contains("Linux") || configuracion.sistemaOperativo.contains("Mac OS")){
            sudoPass = pedirContrasena();
        }
        while(!instalador.instalarSolver(configuracion.sistemaOperativo, configuracion.nombreSolver, sudoPass)){
            opcion = JOptionPane.showOptionDialog(null, "Invalid password", "Warning!!", 
                                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            
            if(opcion == 0)  sudoPass = pedirContrasena();
            else return false;
        }
        return true;
    }
    
    public String pedirContrasena(){
        char[] contrasena;
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Sudo password: ");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"Ok", "Cancel"};
        int opcion = JOptionPane.showOptionDialog(null, panel, "Sudo password",
                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                         null, options, options[0]);
        if(opcion == 0) {
            contrasena = pass.getPassword();
            return new String(contrasena);
        }else{
            return "";
        }
    }
    
    public void descargarVariaMos() throws FileNotFoundException, IOException, MalformedURLException, InterruptedException{
        Object[] opciones = {"Try again", "Cancel"};
        int opcion;
        while(!instalador.descargarVariamosFromURL(configuracion.variamosDl, configuracion.variamosVersion, rutaVariaMos)) {
                opcion = JOptionPane.showOptionDialog(null, "VariaMos could not been downloaded", "Warning!!", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if(opcion == 0) continue;
                if(opcion == 1) System.exit(1);
        }
        JOptionPane.showOptionDialog(null, "VariaMos has been downloaded successfully ", "Message", 
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, acept, acept[0]);
    }
    
    public Configuration getConfiguracion() {
        return configuracion;
    }

    public Installer getInstalador() {
        return instalador;
    }
    
}