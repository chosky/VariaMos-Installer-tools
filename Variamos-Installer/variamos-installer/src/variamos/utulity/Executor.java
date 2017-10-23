package variamos.utulity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import variamos.Variamos;

/**
 *
 * @author José David Henao Ocampo - Monitor EDS
 */
public class Executor {
    
    String ruteVariamos = "";
    public boolean conexion, conexionSolver;
    
    public void Executor(String ruteVariamos){
        this.ruteVariamos = ruteVariamos;
    }
    
    public void executorCode(){
        try {
            //Loading installation configuration
            Configuration configuration = new Configuration();
            configuration.loadConfigurationFile();
            Installer installer = new Installer();
            
            //Downloading solver
            if(configuration.solverDl != null){
                if(installer.downloadSolverFromURL(configuration.solverDl, configuration.solverName))
                    conexionSolver = true;
                else
                    conexionSolver = false;
            }
            installer.installSolver(configuration.operativeSystem, configuration.solverName);
            
            //Configure environment variables
            installer.configureEnvironmentVariables(configuration.operativeSystem);
            
            //Downloading VariaMos 
            if(installer.downloadVariamosFromURL(configuration.variamosDl, configuration.version, ruteVariamos)) 
                conexion = true;
            else
                conexion = false;
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Variamos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Variamos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Variamos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Variamos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Variamos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Variamos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
