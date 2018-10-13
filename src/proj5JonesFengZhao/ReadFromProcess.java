/*
File: ReadFromProcess.java
CS361 Project 5
Names: Yi Feng, Matt Jones, Danqing Zhao
Date: 10/12/18
 */

package proj5JonesFengZhao;

import javafx.application.Platform;

import java.io.InputStream;

public class ReadFromProcess implements Runnable {
    private InputStream input;
    private IOConsole console;

    /**
     * Constructor
     *
     * @param input   Information to be written to the console
     * @param console Reference to IOConsole that will be written to
     */
    ReadFromProcess(InputStream input, IOConsole console) {
        this.input = input;
        this.console = console;
    }

    /**
     * This will find the path of the selected file at the moment the compile button
     * was pressed. The buildProcess function is then called to build a compile process
     * using the javac.
     */
    public void run() {
        try {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) != -1) {
                String result = new String(buffer, 0, length);
                Platform.runLater(() -> console.appendText(result + "\n"));
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
