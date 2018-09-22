package variamos.utility;

import java.io.IOException;

/**
 *
 * @author José David Henao Ocampo - Monitor Especialización en Desarrollo de Software
 *    &    Hassler Castro Cuesta - Monitor Especialización en Diseño Mecánico
 */
public class JavaConfiguratios {
    
    public String[] configureJavaHomeEnvironmentVariable(String operativeSystem, String javaHome) throws IOException, InterruptedException {
        String commands[] = new String[2];
        String javaEnviromentVariablePath = "";
        String javaEnviromentVariableClassPath = "";
        
        if (operativeSystem.contains("Windows")) {
            String path = System.getenv("Path");
            String classpath = System.getenv("CLASSPATH");
            
            String JAVA_HOME = javaHome.substring(0, javaHome.length() - 4);
            String JDK_HOME = "%" + JAVA_HOME + "%";
            String JRE_HOME = javaHome;
            
            if (!path.contains(JAVA_HOME)) {
                path += ";" + JAVA_HOME + "\\bin;";
            }
            
            if (classpath != null) {
                if (!classpath.contains(JDK_HOME + "\\lib;")) {
                    classpath += ";" + JDK_HOME + "\\lib;";
                }
                if (!classpath.contains(JRE_HOME + "\\lib;")){
                    classpath += ";" + JDK_HOME + "\\lib;";
                }
            } else {
                classpath += ";" + JDK_HOME + "\\lib;" + JRE_HOME + "\\lib;";
            }

            javaEnviromentVariablePath =  ";" + JAVA_HOME + "\\bin;";
            javaEnviromentVariableClassPath = ";" + JDK_HOME + "\\lib;" + JRE_HOME + "\\lib;";
            commands[0] = javaEnviromentVariablePath;
            commands[1] = javaEnviromentVariableClassPath;
            
            String[] cmd = {"cmd", "/c", "set Path=\"" + path + "\" && set CLASSPATH=\"" + classpath + "\""};
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        }
        return commands;
    }
    
}
