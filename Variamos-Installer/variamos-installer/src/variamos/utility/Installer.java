package variamos.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author José David Henao Ocampo - Monitor Especialización en Desarrollo de Software
 *    &    Hassler Castro Cuesta - Monitor Especialización en Diseño Mecánico
 */
public class Installer {
    
    public boolean descargarVariamosFromURL(String url, String configuratcion, String ruta) throws MalformedURLException, FileNotFoundException, InterruptedException, IOException {
        System.out.println("Descargando VariaMos " + configuratcion + "...");
        try {
            URL newUrl = new URL(url);

            Path targetPath = new File(ruta + "/.variamos_pre-file.jar").toPath();
            
            Files.copy(newUrl.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            
            Boolean hidden = (Boolean) Files.getAttribute(targetPath, "dos:hidden", LinkOption.NOFOLLOW_LINKS);
            if (hidden != null && !hidden) {
		Files.setAttribute(targetPath, "dos:hidden", Boolean.TRUE, LinkOption.NOFOLLOW_LINKS);
            }
            
            System.out.println("Variamos se descargo correctamente.");
            return true;

        } catch (MalformedURLException mue) {
            System.err.println("Variamos no se descargó correctamente porque el URL está malformado.");
            return false;
        } catch (FileNotFoundException fnfe) {
            System.err.println("Variamos no se descargó correctamente porque el archivo no existe.");
            return false;
        } catch(AccessDeniedException eas){
            System.err.println("Variamos no se descargó correctamente porque la carpeta es de Administrador.");
            return false;
        } catch (IOException ioe) {
            System.err.println("Variamos no se descargó correctamente por operaciones de i/o.");
            System.err.println(ioe);
            return false;
        } catch (Exception e) {
            System.err.println("Variamos no se descargó.");
            return false;
        }
    }
    
    public void launchVariamos(String sistemaOperativo, String rutaLauncher) throws IOException {
        if (sistemaOperativo.contains("Windows")) {
            PrintWriter writer = new PrintWriter(rutaLauncher + "\\Variamos.bat", "UTF-8");
            writer.println("set CLASSPATH=.;C:\\Program Files\\swipl\\lib\\jpl.jar;C:\\Program Files\\swipl\\lib;%CLASSPATH%");
            writer.println("set Path=C:\\Program Files\\swipl\\lib\\jpl.jar;C:\\Program Files\\swipl\\bin;%Path%");
            writer.println("java -jar " + "\"" + rutaLauncher + "\\.variamos_pre-file.jar\"");
            writer.close();
        } else if (sistemaOperativo.contains("Linux")) {
            PrintWriter writer = new PrintWriter(rutaLauncher + "/Variamos.sh", "UTF-8");
            writer.println("export SWI_HOME_DIR=/usr/lib/swi-prolog");
            writer.println("export PATH=$PATH:$SWI_HOME_DIR/lib/:$SWI_HOME_DIR/lib/jpl.jar");
            writer.println("java -jar " + "\"" + rutaLauncher + "/.variamos_pre-file.jar\"");
            writer.close();
        } else if (sistemaOperativo.contains("Mac OS")) {
            PrintWriter writer = new PrintWriter(rutaLauncher + "/Variamos.sh", "UTF-8");
            writer.println("export SWI_HOME_DIR=/Applications/SWI-Prolog.app/Contents/swipl");
            writer.println("export PATH=$PATH:$SWI_HOME_DIR/lib/:$SWI_HOME_DIR/lib/jpl.jar");
            writer.println("export CLASSPATH=$SWI_HOME_DIR/lib/:$SWI_HOME_DIR/lib/jpl.jar");
            writer.println("java -Djava.library.path=$SWI_HOME_DIR:$SWI_HOME_DIR/lib/x86_64-darwin15.6.0/ -jar " + "\"" + rutaLauncher + "/.variamos_pre-file.jar\"");
            writer.close();
        }
    }
    
    public boolean downloadSolverFromURL(String url, String nombreSolver) throws MalformedURLException, FileNotFoundException, IOException {
        try {
            URL _url = new URL(url);
            File solver = new File(System.getProperty("user.dir") + "/" + nombreSolver);
            solver.deleteOnExit();
            Path targetPath = solver.toPath();

            Files.copy(_url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (MalformedURLException mue) {
            System.err.println("SWI-Prolog no se descargó correctamente porque el URL está malformado.");
            return false;
        } catch (FileNotFoundException fnfe) {
            System.err.println("SWI-Prolog no se descargó correctamente porque el archivo no existe.");
            return false;
        } catch(AccessDeniedException eas){
            System.err.println("SWI-Prolog no se descargó correctamente porque la carpeta es de Administrador.");
            return false;
        } catch (IOException ioe) {
            System.err.println("SWI-Prolog no se descargó correctamente por operaciones de i/o.");
            return false;
        } catch (Exception e) {
            System.err.println("SWI-Prolog no se descargó.");
            return false;
        }
    }
    
    public boolean instalarSolver(String sistemaOperativo, String nombreSolver, String sudoPass) throws IOException, InterruptedException {
        Process process = null;
        if (sistemaOperativo.contains("Windows")) {
            String[] cmd = {"cmd", "/c", System.getProperty("user.dir") + "\\" + nombreSolver + "\""};
            process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        } else if (sistemaOperativo.contains("Linux")) {
            if(!sudoTest(sudoPass)) return false;
            runCommand("echo " + sudoPass + "| sudo -S apt-get update");
            runCommand("echo " + sudoPass + "| sudo -S apt-get install default-jre");
            runCommand("echo " + sudoPass + "| sudo -S apt-add-repository ppa:swi-prolog/stable");
            runCommand("echo " + sudoPass + "| sudo -S apt-get update");
            runCommand("echo " + sudoPass + "| sudo -S apt-get --assume-yes install swi-prolog");
            runCommand("echo " + sudoPass + "| sudo -S apt-get --assume-yes install swi-prolog-java");
        } else if (sistemaOperativo.contains("Mac OS")) {
            if(!sudoTest(sudoPass)) return false;
            if(!preConfigurationOSX()) {
                runCommand("echo " + sudoPass + "| sudo -S mkdir /usr/local/");
                runCommand("echo " + sudoPass + "| sudo -S mkdir /usr/local/lib/");
                runCommand("echo " + sudoPass + "| sudo export PATH=$PATH:/usr/local/lib/");
            }
            runCommand("echo " + sudoPass + "| sudo -S hdiutil attach " + nombreSolver);
            runCommand("echo " + sudoPass + "| sudo -S cp -R /Volumes/SWI-Prolog/SWI-Prolog.app /Applications");
            runCommand("hdiutil unmount /Volumes/SWI-Prolog/");
            runCommand("echo " + sudoPass + "| sudo -S cp /Applications/SWI-Prolog.app/Contents/swipl/lib/x86_64-darwin15.6.0/* /usr/local/lib/");
        }
        return true;
    }
    
    public void runCommand(String cmd) throws IOException, InterruptedException {
        String[] cmdComplete = {"/bin/bash", "-c", cmd};
        System.out.println(cmd.substring(cmd.indexOf("|")+2, cmd.length()));
        String s;
        Process process = Runtime.getRuntime().exec(cmdComplete);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        process.waitFor();
        System.out.println("exit: " + process.exitValue());
        process.destroy();
    }
     
    public void configureEnvironmentVariables(String sistemaOperativo) throws IOException, InterruptedException {
        if (sistemaOperativo.contains("Windows")) {
            String path = System.getenv("Path");
            String classpath = System.getenv("CLASSPATH");

            if (!path.contains(System.getenv("ProgramFiles") + "\\swipl\\lib\\jpl.jar;" + System.getenv("ProgramFiles") + "\\swipl\\bin;")) {
                path += ";" + System.getenv("ProgramFiles") + "\\swipl\\lib\\jpl.jar;" + System.getenv("ProgramFiles") + "\\swipl\\bin;";
            }
            if (classpath != null) {
                if (!classpath.contains(System.getenv("ProgramFiles") + "\\swipl\\lib\\jpl.jar;" + System.getenv("ProgramFiles") + "\\swipl\\lib;")) {
                    classpath += ";" + System.getenv("ProgramFiles") + "\\swipl\\lib\\jpl.jar;" + System.getenv("ProgramFiles") + "\\swipl\\lib;";
                }
            } else {
                classpath += ";" + System.getenv("ProgramFiles") + "\\swipl\\lib\\jpl.jar;" + System.getenv("ProgramFiles") + "\\swipl\\lib;";
            }

            String[] cmd = {"cmd", "/c", "set Path=\"" + path + "\" && setx CLASSPATH \"" + classpath + "\""};
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        }
    }
    
    public boolean sudoTest(String pass) throws IOException, InterruptedException{
        String[] cmdComplete = {"/bin/bash", "-c", "echo " + pass + "| sudo -S ls"};
        Process process = Runtime.getRuntime().exec(cmdComplete);
        process.waitFor();
        if(process.exitValue() == 0){
            process.destroy();
            return true;
        }
        process.destroy();
        return false;
    }
    
    public boolean preConfigurationOSX() throws IOException, InterruptedException {
        String[] cmdComplete = {"/bin/bash", "-c", "ls /usr/local/lib/"};
        Process process = Runtime.getRuntime().exec(cmdComplete);
        process.waitFor();
        if(process.exitValue() == 0){
            process.destroy();
            return true;
        }
        process.destroy();
        return false;
    }
    
}