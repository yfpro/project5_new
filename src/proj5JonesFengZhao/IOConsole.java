package proj5JonesFengZhao;

import javafx.scene.input.KeyCode;
import org.fxmisc.richtext.StyleClassedTextArea;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


class IOConsole extends StyleClassedTextArea {
    private OutputStream outputStream;
    private OutputStream errorStream;
    private String input;

    IOConsole() {
        input = "";
        this.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
    }

    void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Read from the inputStream of process and write to the styleClassedTextArea
     *
     * @param input inputStream got from the process
     */
    void readFrom(InputStream input) {

        // read the input using BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(input));

        String line;
        try {
            while ((line = br.readLine()) != null) {
                this.appendText(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeTo() {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(outputStream));

            System.out.println("Writing " + input + " to the OutputStream.");
            writer.write(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleKeyPress(KeyCode code) {
        if (code.equals(KeyCode.ENTER)) {
            writeTo();
            input = "";
        } else {
            if (code.isLetterKey() | code.isDigitKey()) {
                input += code.getName();
            }
        }
    }
}
