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

/**
 * This IOConsole class extends the StyleCalssedTextArea.
 * It 
 * 
 */
public class IOConsole extends StyleClassedTextArea {
    private OutputStream outputStream;
    private String userInput;

    /**
     * Constructor
     */
    IOConsole() {
        userInput = "";
        this.setOnKeyTyped(event -> handleKeyPress(event));
    }

    /**
     * set the console's output stream to the input OutpusStream
     * @param outputStream
     */
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

    //TODO: WHAT DOES THIS FUNCTION DO?
    public void writeTo() {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            System.out.println("Writing " + userInput + " to the OutputStream.");
            writer.write(userInput);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    //TODO: Are we using handleKeyTyped or Pressed?
    private void handleKeyPress(KeyEvent keyEvent) {
        userInput += keyEvent.getCharacter();
        if (keyEvent.getCharacter().equals("\r")) {
            userInput += "\n";
            this.writeTo();
            userInput = "";
        }
    }
}
