package proj5JonesFengZhao;

import org.fxmisc.richtext.StyleClassedTextArea;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

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
            Scanner scan = new Scanner(this.getText());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

            String line;
            try {
                while ((line = scan.nextLine()) != null) {
                    this.appendText(line + "\n");
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            String input = scan.nextLine();
            input += "\n";
            writer.write(input);
            System.out.println(input);
            writer.flush();
//            writer.close();

//            System.out.println("Hello");
//            System.out.println(output);
            output.write(this.getText().getBytes(Charset.defaultCharset()));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
