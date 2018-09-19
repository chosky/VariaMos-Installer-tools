package variamos.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author José David Henao Ocampo - Monitor Especialización en Desarrollo de Software
 *    &    Hassler Castro Cuesta - Monitor Especialización en Diseño Mecánico
 */
public class Configuration {
    public String variamosVersion, variamosDl, solverVersion, solverDl, operativeSystem, operativeSystemVersion, solverName, versionJava, javaPath;
    
    public Configuration(){
        versionJava = System.getProperty("java.version");
        operativeSystem = System.getProperty("os.name");
        operativeSystemVersion = System.getProperty("sun.arch.data.model");
        javaPath = System.getProperty("java.home");
        solverName = "solver";
        if (operativeSystem.contains("Windows")) {
            solverName += ".exe";
        } else if (operativeSystem.contains("Mac OS")) {
            solverName += ".dmg";
        }
    }
    
    public void loadConfigurationFile() throws SAXException, IOException, ParserConfigurationException {
        File archivo = new File("config.xml");
        archivo.deleteOnExit();
        InputStream is = getClass().getResourceAsStream("/configuration_files/configuration.xml");
        OutputStream outputStream = new FileOutputStream(archivo);

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = is.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }

        outputStream.close();
        is.close();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(archivo);
        variamosVersion = document.getElementsByTagName("variamos-version").item(0).getTextContent();
        solverVersion = document.getElementsByTagName("solver-version").item(0).getTextContent();
        variamosDl = document.getElementsByTagName("variamos-dl").item(0).getTextContent();
        if (operativeSystem.contains("Windows")) {
            if (operativeSystemVersion.equals("64")) {
                solverDl = document.getElementsByTagName("Windowsx64").item(0).getTextContent();
            } else if (operativeSystemVersion.equals("32")) {
                solverDl = document.getElementsByTagName("Windowsx86").item(0).getTextContent();
            }
        } else if (operativeSystem.contains("Mac OS")) {
            solverDl = document.getElementsByTagName("OSX").item(0).getTextContent();
        }
    }
    
}
