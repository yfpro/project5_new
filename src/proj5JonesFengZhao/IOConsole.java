package proj5JonesFengZhao;

import org.fxmisc.richtext.StyleClassedTextArea;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class IOConsole extends StyleClassedTextArea{

    private OutputStream outputStream;
    private OutputStream errorStream;

    /**
     * Read from the inputStream of process and write to the styleClassedTextArea
     *
     * @param input inputStream got from the process
     */
    public void readFrom(InputStream input){

            // read the input using BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(input));

            String line;
            try {
                while ((line = br.readLine()) != null) {
                    this.appendText(line+"\n");
                }
            }
            catch (Exception e){
                System.out.println(e);
            }


    }

    public void writeTo(OutputStream output){
        try {
            output.write(this.getText().getBytes(Charset.defaultCharset()));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
