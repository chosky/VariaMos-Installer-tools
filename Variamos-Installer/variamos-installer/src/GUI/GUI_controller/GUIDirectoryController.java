package GUI.GUI_controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author José David Henao Ocampo - Monitor Especialización en Desarrollo de Software
 *    &    Hassler Castro Cuesta - Monitor Especialización en Diseño Mecánico
 */
public class GUIDirectoryController {
    
    public String routeExecutor() throws FileNotFoundException, InterruptedException, IOException  {
        String route = giveDirectoryPath();
        String message = testRoute(route);
        while(!message.equals("0")){
            JOptionPane.showMessageDialog(null, message);
            route = giveDirectoryPath();
            message = testRoute(route);
        }
        return route;
    }
    
    public String giveDirectoryPath (){
        JFileChooser dirSelector = new JFileChooser();
        dirSelector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dirSelector.showOpenDialog(dirSelector);
        File directory = dirSelector.getSelectedFile();
        String directoryStr = directory.toString();
        return directoryStr;
    }
    
    public String testRoute(String route) throws MalformedURLException, FileNotFoundException, InterruptedException, IOException{
        try {
            File file = new File(route + "/prueba.txt");
            file.deleteOnExit();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.close();
            return "0";
        } catch (Exception e) {
            return "Access denied. This rute doesn't exist or you don't have admin permissions";
        }
    }
    
}