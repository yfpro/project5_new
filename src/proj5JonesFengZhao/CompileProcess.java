package proj5JonesFengZhao;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;


public class CompileProcess implements Runnable {
    private File curFile;
    private IOConsole console;
    private Process process;
    private boolean ifRun = false;

    CompileProcess(File curFile, IOConsole console, boolean ifRun) {
        this.curFile = curFile;
        this.console = console;
        this.ifRun = ifRun;
    }

    public void run() {
        String path = curFile.getAbsolutePath();
        String[] command = {"javac", path};
        this.process = buildProcess(console, command);
        if(ifRun){
            if (this.process!=null) {
                path = curFile.getAbsoluteFile().getParent();
                String fileName = curFile.getName();
                String[] runCommand = {"java", "-cp", path, fileName.substring(0,
                        fileName.length() - 5)};
                this.process = buildProcess(console, runCommand);
            }
        }
    }
    public Process getProcess(){
        return(this.process);
    }
    public Process buildProcess(IOConsole console, String[] command) {
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            InputStream i = process.getErrorStream();
            console.readFrom(i);

            InputStream processOutput = process.getInputStream();
            console.readFrom(processOutput);

            OutputStream processInput = process.getOutputStream();
            console.setOutputStream(processInput);
            int errCode = process.waitFor();
            this.process = process;
            return(process);
        } catch (Exception e) {
            e.printStackTrace();
            return(null);
        }
    }
}
