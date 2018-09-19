package variamos.utility;

import java.io.IOException;

/**
 *
 * @author José David Henao Ocampo - Monitor Especialización en Desarrollo de Software
 *    &    Hassler Castro Cuesta - Monitor Especialización en Diseño Mecánico
 */
public class JavaConfiguratios {
    public void configureJavaHomeEnvironmentVariable(String operativeSystem) throws IOException, InterruptedException {
        if (operativeSystem.contains("Windows")) {
            String path = System.getenv("Path");
            String classpath = System.getenv("CLASSPATH");
            String programFiles = System.getenv("ProgramFiles");

            if (!path.contains(programFiles + "\\swipl\\lib\\jpl.jar;" + programFiles + "\\swipl\\bin;")) {
                path += ";" + System.getenv("ProgramFiles") + "\\swipl\\lib\\jpl.jar;" + System.getenv("ProgramFiles") + "\\swipl\\bin;";
            }
            if (classpath != null) {
                if (!classpath.contains(programFiles + "\\swipl\\lib\\jpl.jar;" + programFiles + "\\swipl\\lib;")) {
                    classpath += ";" + programFiles + "\\swipl\\lib\\jpl.jar;" + programFiles + "\\swipl\\lib;";
                }
            } else {
                classpath += ";" + programFiles + "\\swipl\\lib\\jpl.jar;" + programFiles + "\\swipl\\lib;";
            }

            String[] cmd = {"cmd", "/c", "set Path=\"" + path + "\" && setx CLASSPATH \"" + classpath + "\""};
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        }
    }
}
