package workWithFiles;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

    private FileWriter fr;

    public WriteToFile(String fileName) throws IOException {
        fr = new FileWriter(fileName, true);
    }

    public void writing(String line) throws IOException {
        try {
            fr.write(line);
            fr.close();
        }
        catch (IOException e){

        }
    }
}
