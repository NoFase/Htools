package workWithFiles;

import java.io.*;

public class ReadFromFile {
    private BufferedReader br;
    private StringBuffer inputLines = new StringBuffer();


    public ReadFromFile(String fileName) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(fileName));
    }

    public StringBuffer reading() throws IOException {
        while (br.ready()){
            String line = br.readLine();
            inputLines.append(line);
            inputLines.append("\n");
        }
        br.close();
        return inputLines;
    }

}
