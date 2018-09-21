package variamos.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
public class VariaMosConfiguratios {
    public boolean downloadVariamosFromURL(String url, String configuration, String route) throws MalformedURLException, FileNotFoundException, InterruptedException, IOException {
        System.out.println("Downloading VariaMos " + configuration + "...");
        try {
            URL newUrl = new URL(url);
            
            Path targetPath = new File(route + "/Variamos-Resources/variamos_pre-file.jar").toPath();
            
            Files.copy(newUrl.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("Variamos it was downloaded.");
            return true;

        } catch (MalformedURLException mue) {
            System.err.println("Variamos it was not downloaded correctly because the URL is malformed.");
            return false;
        } catch (FileNotFoundException fnfe) {
            System.err.println("Variamos it was not downloaded correctly because the file does not exist.");
            return false;
        } catch(AccessDeniedException eas){
            System.err.println("Variamos it was not downloaded correctly because the folder is from Administrator.");
            return false;
        } catch (IOException ioe) {
            System.err.println("Variamos it was not downloaded correctly by i/o operations.");
            return false;
        } catch (Exception e) {
            System.err.println("Variamos it was not downloaded.");
            return false;
        }
    }
    
    public void launchVariamos(String operativeSystem, String launcherRoute) throws IOException {
        if (operativeSystem.contains("Windows")) {
            PrintWriter writer = new PrintWriter(launcherRoute + "\\Variamos-Resources\\Variamos.bat", "UTF-8");
            writer.println("@echo off");
            writer.println("set CLASSPATH=.;C:\\Program Files\\swipl\\lib\\jpl.jar;C:\\Program Files\\swipl\\lib;%CLASSPATH%");
            writer.println("set Path=C:\\Program Files\\swipl\\lib\\jpl.jar;C:\\Program Files\\swipl\\bin;%Path%");
            writer.println("java -jar " + "\"" + launcherRoute + "\\Variamos-Resources\\variamos_pre-file.jar\"");
            writer.close();
        } else if (operativeSystem.contains("Linux")) {
            PrintWriter writer = new PrintWriter(launcherRoute + "/Variamos-Resources/Variamos.sh", "UTF-8");
            writer.println("export SWI_HOME_DIR=/usr/lib/swi-prolog");
            writer.println("export PATH=$PATH:$SWI_HOME_DIR/lib/:$SWI_HOME_DIR/lib/jpl.jar");
            writer.println("java -jar " + "\"" + launcherRoute + "/Variamos-Resources/variamos_pre-file.jar\"");
            writer.close();
        } else if (operativeSystem.contains("Mac OS")) {
            PrintWriter writer = new PrintWriter(launcherRoute + "/Variamos-Resources/Variamos.sh", "UTF-8");
            writer.println("export SWI_HOME_DIR=/Applications/SWI-Prolog.app/Contents/swipl");
            writer.println("export PATH=$PATH:$SWI_HOME_DIR/lib/:$SWI_HOME_DIR/lib/jpl.jar");
            writer.println("export CLASSPATH=$SWI_HOME_DIR/lib/:$SWI_HOME_DIR/lib/jpl.jar");
            writer.println("java -Djava.library.path=$SWI_HOME_DIR:$SWI_HOME_DIR/lib/x86_64-darwin15.6.0/ -jar " + "\"" + launcherRoute + "/Variamos-Resources/variamos_pre-file.jar\"");
            writer.close();
        }
    }
}
