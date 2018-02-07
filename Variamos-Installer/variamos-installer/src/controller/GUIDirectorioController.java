package controller;

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
public class GUIDirectorioController {
    
    public String ejecucionDeRuta() throws FileNotFoundException, InterruptedException, IOException  {
        String ruta = darRutaDeDirectorio();
        String mensaje = pruebaRuta(ruta);
        while(!mensaje.equals("0")){
            JOptionPane.showMessageDialog(null, mensaje);
            ruta = darRutaDeDirectorio();
            mensaje = pruebaRuta(ruta);
        }
        return ruta;
    }
    
    public String darRutaDeDirectorio (){
        JFileChooser dirSelector = new JFileChooser();
        dirSelector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dirSelector.showOpenDialog(dirSelector);
        File directory = dirSelector.getSelectedFile();
        String directoryStr = directory.toString();
        return directoryStr;
    }
    
    public String pruebaRuta(String ruta) throws MalformedURLException, FileNotFoundException, InterruptedException, IOException{
        try {
            File archivo = new File(ruta + "/prueba.txt");
            archivo.deleteOnExit();
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            bw.close();
            return "0";
        } catch (Exception e) {
            return "Access denied. This rute doesn't exist or you don't have admin permissions";
        }
    }
    
}
