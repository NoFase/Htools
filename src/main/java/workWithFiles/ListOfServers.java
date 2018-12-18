package workWithFiles;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static staticVariable.StaticVariables.listOfServers;

public class ListOfServers {


    public void reading(){
        try {
            ReadFromFile rf = new ReadFromFile("src\\main\\resources\\OtherFiles\\servers.txt");

            String[] lines = rf.reading().toString().split("\n");
            for (String x: lines){
                String[] partOfLine = x.split(":");
//                первая часть это название вторая это IP
                if (partOfLine.length > 1)listOfServers.put(partOfLine[0], new String[]{partOfLine[1], partOfLine[2]});
//                else listOfServers.put("none", "none");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("не нахожу файла servers.txt");
        }
    }

    public void writing(String abr) throws IOException {
        String name, ip;
        name = listOfServers.get(abr)[0];
        ip = listOfServers.get(abr)[1];
        new WriteToFile("OtherFiles/servers.txt").writing(abr + ":" + name + ":" + ip + "\n");
    }
}
