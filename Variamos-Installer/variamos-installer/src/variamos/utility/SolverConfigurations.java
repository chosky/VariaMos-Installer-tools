package variamos.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author José David Henao Ocampo - Monitor Especialización en Desarrollo de Software
 *    &    Hassler Castro Cuesta - Monitor Especialización en Diseño Mecánico
 */
public class SolverConfigurations {
    public boolean downloadSolverFromURL(String url, String solverName) throws MalformedURLException, FileNotFoundException, IOException {
        try {
            URL _url = new URL(url);
            File solver = new File(System.getProperty("user.dir") + "/" + solverName);
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
    
    public boolean instalarSolver(String operativeSystem, String solverName, String sudoPass) throws IOException, InterruptedException {
        Process process = null;
        if (operativeSystem.contains("Windows")) {
            String[] cmd = {"cmd", "/c", System.getProperty("user.dir") + "\\" + solverName + "\""};
            process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            process.destroy();
        } else if (operativeSystem.contains("Linux")) {
            if(!sudoTest(sudoPass)) return false;
            runCommand("echo " + sudoPass + "| sudo -S apt-get update");
            runCommand("echo " + sudoPass + "| sudo -S apt-get install default-jre");
            runCommand("echo " + sudoPass + "| sudo -S apt-add-repository ppa:swi-prolog/stable");
            runCommand("echo " + sudoPass + "| sudo -S apt-get update");
            runCommand("echo " + sudoPass + "| sudo -S apt-get --assume-yes install swi-prolog");
            runCommand("echo " + sudoPass + "| sudo -S apt-get --assume-yes install swi-prolog-java");
        } else if (operativeSystem.contains("Mac OS")) {
            if(!sudoTest(sudoPass)) return false;
            if(!preConfigurationOSX()) {
                runCommand("echo " + sudoPass + "| sudo -S mkdir /usr/local/");
                runCommand("echo " + sudoPass + "| sudo -S mkdir /usr/local/lib/");
                runCommand("echo " + sudoPass + "| sudo export PATH=$PATH:/usr/local/lib/");
            }
            runCommand("echo " + sudoPass + "| sudo -S hdiutil attach " + solverName);
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
     
    public void configureSwiPlEnvironmentVariable(String operativeSystem) throws IOException, InterruptedException {
        if (operativeSystem.contains("Windows")) {
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
