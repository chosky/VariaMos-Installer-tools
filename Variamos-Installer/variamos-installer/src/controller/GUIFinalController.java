package controller;

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
    
    public void opcionesDeLauncherYFinal(boolean accesoDirecto, boolean iniciarVariaMos, String sistemaOperativo, String rutaJar) throws IOException {
        if (accesoDirecto) {
            if (iniciarVariaMos) {
                if(sistemaOperativo.contains("Windows")) {
                    PrintWriter writer = new PrintWriter("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\Variamos_Launcher.bat", "UTF-8");
                    writer.println("\"" + rutaJar + "/Variamos_Launcher.bat\"");
                    writer.close();
                } else if (sistemaOperativo.contains("Linux") || sistemaOperativo.contains("Mac OS")) {
                    generarLauncher(sistemaOperativo, rutaJar);
                }
                ejecutarVariamos(sistemaOperativo, rutaJar);
            } else {
                if(sistemaOperativo.contains("Windows")) {
                    PrintWriter writer = new PrintWriter("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\Variamos_Launcher.bat", "UTF-8");
                    writer.println("\"" + rutaJar + "/Variamos_Launcher.bat\"");
                    writer.close();
                } else if (sistemaOperativo.contains("Linux") || sistemaOperativo.contains("Mac OS")) {
                    generarLauncher(sistemaOperativo, rutaJar);
                }
            }

        } else {
            if (iniciarVariaMos) {
                ejecutarVariamos(sistemaOperativo, rutaJar);
            }
        }
    }
    
    public void ejecutarVariamos(String sistemaOperativo, String ruteJar) throws IOException {
        Process process = null;
        if (sistemaOperativo.contains("Windows")) {
            String[] cmd = {"cmd", "/c", ruteJar + "\\Variamos_Launcher.bat"};
            process = Runtime.getRuntime().exec(cmd);
        } else {
            process = Runtime.getRuntime().exec(new String[]{"bash", "-c", "sh " + ruteJar + "/Variamos_Launcher.sh"});
        }
    }
    
    private void generarLauncher(String sistemaOperativo, String rutaJar) throws IOException, FileNotFoundException {
        String idioma = Locale.getDefault().getLanguage();
        //String descarga = selectorIdioma(idioma);
        String descarga = "";
        String[] carpetasIdioma = {"Escritorio", "Desktop", "Bureau"};
        
        if(sistemaOperativo.contains("Linux")) descarga = "/home/";
        else if(sistemaOperativo.contains("Mac OS")) descarga = "/Users/";

        for(int i = 0; i < carpetasIdioma.length; ++i) {
            try {
                PrintWriter writer = new PrintWriter(descarga + System.getProperty("user.name") + "/" + carpetasIdioma[i] + "/Variamos_Launcher.sh", "UTF-8");
                writer.println("sh " + "\"" + rutaJar + "/Variamos_Launcher.sh\"");
                writer.close();
                System.out.println("YA ESTA TODO CORRECTO, NO LE PRESTES ATENCION A ESOS ERRORES RAROS :) !!!!!");
            } catch (FileNotFoundException fnfe) {
                System.err.println(fnfe);
            } catch (IOException ioe) {
                System.err.println(ioe);
            } catch (Exception e) {
                System.err.println(e);
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