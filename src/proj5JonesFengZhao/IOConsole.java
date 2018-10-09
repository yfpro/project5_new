package proj5JonesFengZhao;

import org.fxmisc.richtext.StyleClassedTextArea;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class IOConsole extends StyleClassedTextArea{

    private InputStream inputStream;
    private OutputStream outputStream;
    private OutputStream errorStream;

    public void readFrom(InputStream i){
            inputStream = i;

            // read the input using BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(i));

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

}
