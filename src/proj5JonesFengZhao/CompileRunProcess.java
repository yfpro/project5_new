package proj5JonesFengZhao;

import java.io.File;


public class CompileRunProcess extends CompileProcess {
    private File curFile;
    private IOConsole console;

    CompileRunProcess(File curFile, IOConsole console) {
        super(curFile, console);
    }

    @Override
    public void run() {
        String path = curFile.getAbsolutePath();
        String[] compileCommand = {"javac", path};

        if (buildProcess(console, compileCommand)) {
            path = curFile.getAbsoluteFile().getParent();
            String fileName = curFile.getName();
            String[] runCommand = {"java", "-cp", path, fileName.substring(0,
                    fileName.length() - 5)};
            buildProcess(console, runCommand);
        }
    }
}
