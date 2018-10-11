package proj5JonesFengZhao;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import org.fxmisc.richtext.StyleClassedTextArea;

import java.io.*;


public class IOConsole extends StyleClassedTextArea {
    private OutputStream outputStream;
    private OutputStream errorStream;
    private String input;

    IOConsole() {
        input = "";
        this.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
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
        try {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) != -1) {
                String result = new String(buffer, 0, length);
                Platform.runLater(() -> this.appendText(result + "\n"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeTo(OutputStream outputStream) {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            System.out.println("Writing " + input + " to the OutputStream.");
            writer.write(input);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void handleKeyPress(KeyCode code) {
        if (code.equals(KeyCode.ENTER)) {
            input = "";
        } else {
            if (code.isLetterKey() | code.isDigitKey() | code.isWhitespaceKey()) {
                input += code.getName();
            }
        }
    }
}
