package GUI.GUI_controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 *
 * @author José David Henao Ocampo - Monitor Especialización en Desarrollo de Software
 *    &    Hassler Castro Cuesta - Monitor Especialización en Diseño Mecánico
 */
public class GUIFinalController {
    
    public void opcionesDeLauncherYFinal(boolean directAccess, boolean startVariaMos, String operativeSystem, String jarRoute) throws IOException {
        if (directAccess) {
            if (startVariaMos) {
                if(operativeSystem.contains("Windows")) {
                    PrintWriter writer = new PrintWriter("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\Variamos.bat", "UTF-8");
                    writer.println("\"" + jarRoute + "\\Variamos-Resources\\Variamos.bat\"");
                    writer.close();
                } else if (operativeSystem.contains("Linux") || operativeSystem.contains("Mac OS")) {
                    generateLauncher(operativeSystem, jarRoute);
                }
                executeVariamos(operativeSystem, jarRoute);
            } else {
                if(operativeSystem.contains("Windows")) {
                    PrintWriter writer = new PrintWriter("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\Variamos.bat", "UTF-8");
                    writer.println("\"" + jarRoute + "\\Variamos-Resources\\Variamos.bat\"");
                    writer.close();
                } else if (operativeSystem.contains("Linux") || operativeSystem.contains("Mac OS")) {
                    generateLauncher(operativeSystem, jarRoute);
                }
            }
        } else {
            if (startVariaMos) {
                executeVariamos(operativeSystem, jarRoute);
            }
        }
    }
    
    public void executeVariamos(String operativeSystem, String jarRoute) throws IOException {
        Process process = null;
        if (operativeSystem.contains("Windows")) {
            String[] cmd = {"cmd", "/c", jarRoute + "\\Variamos-Resources\\Variamos.bat"};
            process = Runtime.getRuntime().exec(cmd);
        } else {
            process = Runtime.getRuntime().exec(new String[]{"bash", "-c", "sh " + jarRoute + "/Variamos-Resources/Variamos.sh"});
        }
    }
    
    private void generateLauncher(String operativeSystem, String jarRoute) throws IOException, FileNotFoundException {
        //String idioma = Locale.getDefault().getLanguage();
        //String descarga = selectorIdioma(idioma);
        String download = "";
        String[] languageFolders = {"Escritorio", "Desktop", "Bureau"};
        
        if(operativeSystem.contains("Linux")) download = "/home/";
        else if(operativeSystem.contains("Mac OS")) download = "/Users/";

        for(int i = 0; i < languageFolders.length; ++i) {
            try {
                PrintWriter writer = new PrintWriter(download + System.getProperty("user.name") + "/" + languageFolders[i] + "/Variamos.sh", "UTF-8");
                writer.println("sh " + "\"" + jarRoute + "/Variamos-Resources/Variamos.sh\"");
                writer.close();
            } catch (Exception e) {
            }
        }
    }

    private String selectorIdioma(String idioma) {
        switch(idioma) {
            case "es":
                System.out.println("Español");
                return "Escritorio";
            case "en":
                System.out.println("Ingles");
                return "Desktop";
            case "fr":
                System.out.println("Frances");
                return "Bureau";
        }
        return "Escritorio";
    }

}