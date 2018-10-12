/*
File: IOConsole.java
CS361 Project 5
Names: Yi Feng, Matt Jones, Danqing Zhao
Date: 10/12/18
 */

package proj5JonesFengZhao;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.fxmisc.richtext.StyleClassedTextArea;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;


public class IOConsole extends StyleClassedTextArea {
    private OutputStream outputStream;
    private OutputStream errorStream;
    private String input;

    IOConsole() {
        input = "";
        this.setOnKeyTyped(event -> handleKeyPress(event));
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Read from the inputStream of process and write to the styleClassedTextArea
     *
     * @param input inputStream got from the process
     */
    public void readFrom(InputStream input) {
        Thread readFrom = new Thread(new ReadFromProcess(input, this));
        readFrom.run();
    }

    public void writeTo() {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            System.out.println("Writing " + input + " to the OutputStream.");
            writer.write(input);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void handleKeyPress(KeyEvent keyEvent) {
        input += keyEvent.getCharacter();
        if (keyEvent.getCharacter().equals("\r")) {
            input += "\n";
            this.writeTo();
            input = "";
        }
    }
}
