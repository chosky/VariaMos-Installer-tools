package variamos.utulity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author José David Henao Ocampo - Monitor EDS
 */
public class Installer {
    
    public void downloadVariamosFromURL(String url, String configuration) throws MalformedURLException, FileNotFoundException, IOException, InterruptedException {
        System.out.println("Downloading VariaMos " + configuration + "...");
        URL newUrl = new URL(url);

        Path targetPath = new File(System.getProperty("use      "
                + ""
                + "r.dir") + "/variamos.jar").toPath();
        
        Files.copy(newUrl.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Variamos has been downloaded successfully.");
    }
    
    public void launchVariamos(String operativeSystem, String rute, boolean execute) throws IOException {
        Process process = null;
        if (operativeSystem.contains("Windows")) {
            PrintWriter writer = new PrintWriter(rute+".bat", "UTF-8");
            writer.println("set Path=C:\\Program Files\\swipl\\lib\\jpl.jar;C:\\Program Files\\swipl\\bin;%Path%");
            writer.println("java -jar variamos.jar");
            writer.close();
            if (execute) {
                String[] cmd = {"cmd", "/c", "Variamos_Launcher.bat"};
                process = Runtime.getRuntime().exec(cmd);
            }
        } else if (operativeSystem.contains("Linux")) {
            PrintWriter writer = new PrintWriter(rute + ".sh", "UTF-8");
            writer.println("export SWI_HOME_DIR=/usr/lib/swi-prolog");
            writer.println("export PATH=$PATH:$SWI_HOME_DIR/lib/:$SWI_HOME_DIR/lib/jpl.jar");
            writer.println("java -jar variamos.jar");
            writer.close();
            if(execute) 
                process = Runtime.getRuntime().exec(new String[]{"bash", "-c", "sh Variamos_Launcher.sh"});
        } else if (operativeSystem.contains("Mac OS")) {
            PrintWriter writer = new PrintWriter(rute + ".sh", "UTF-8");
            writer.println("export SWI_HOME_DIR=/Applications/SWI-Prolog.app/Contents/swipl");
            writer.println("export PATH=$PATH:$SWI_HOME_DIR/lib/:$SWI_HOME_DIR/lib/jpl.jar");
            writer.println("export CLASSPATH=$SWI_HOME_DIR/lib/:$SWI_HOME_DIR/lib/jpl.jar");
            writer.println("java -Djava.library.path=$SWI_HOME_DIR:$SWI_HOME_DIR/lib/x86_64-darwin14.3.0/ -jar variamos.jar");
            writer.close();
            if(execute)
                process = Runtime.getRuntime().exec(new String[]{"bash", "-c", "sh Variamos_Launcher.sh"});
        }
    }
    
    public void downloadSolverFromURL(String url, String solverName) throws MalformedURLException, FileNotFoundException, IOException {
        URL _url = new URL(url);
        File solver = new File(System.getProperty("user.dir") + "/" + solverName);
        solver.deleteOnExit();
        Path targetPath = solver.toPath();

        Files.copy(_url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
    }
    
    public void installSolver(String operativeSystem, String solverName) throws IOException, InterruptedException {
        Process process = null;
        if (operativeSystem.contains("Windows")) {
            String[] cmd = {"cmd", "/c", System.getProperty("user.dir") + "\\" + solverName + "\""};
            process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        } else if (operativeSystem.contains("Linux")) {
            runCommand("sudo apt-add-repository ppa:swi-prolog/stable");
            runCommand("sudo apt-get update");
            runCommand("sudo apt-get --assume-yes install swi-prolog");
            runCommand("sudo apt-get --assume-yes install swi-prolog-java");
        } else if (operativeSystem.contains("Mac OS")) {
            runCommand("sudo hdiutil attach " + solverName);
            runCommand("sudo cp -R /Volumes/SWI-Prolog/SWI-Prolog.app /Applications");
            runCommand("hdiutil unmount /Volumes/SWI-Prolog/");
            runCommand("sudo cp /Applications/SWI-Prolog.app/Contents/swipl/lib/x86_64-darwin14.3.0/* /usr/local/lib/");
        }
    }
    
    public void runCommand(String cmd) throws IOException, InterruptedException {
        String s;
        Process process = Runtime.getRuntime().exec(cmd);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((s = br.readLine()) != null) {
            //GUI.Installer.printTxt.setText(s + "\n");
            System.out.println(s);
        }
        process.waitFor();
        //GUI.Installer.printTxt.setText("exit: " + process.exitValue() + "\n");
        System.out.println("exit: " + process.exitValue());
        process.destroy();
        //GUI.Installer.printTxt.setText("Script executed successfully\n");
        System.out.println("Script executed successfully");
    }
     
    public void configureEnvironmentVariables(String operativeSystem) throws IOException, InterruptedException {
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
 
}