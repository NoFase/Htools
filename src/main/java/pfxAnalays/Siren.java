package pfxAnalays;

import workWithFiles.ReadFromFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Siren {
    private ArrayList<String> numberPfx = new ArrayList<>();

    public ArrayList<String> getNumberPfx() {
        return numberPfx;
    }

    public Siren() {
        try {
            ReadFromFile rf = new ReadFromFile("src/main/resources/OtherFiles/pfxSPB.txt");
            numbering(rf.reading().toString());
//            System.out.println("----");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String l: numberPfx){
            System.out.println(l);
        }
    }

    private void numbering(String line){
        String[] l1 = line.split("\n");
        for (String l: l1) {
            String[] lines;
            if (l.contains("-")) {
                lines = l.split("-");
                StringBuffer pfx1 = new StringBuffer(lines[0]);
                int number1 = Integer.parseInt(pfx1.substring(pfx1.length() - 1).trim());
                StringBuffer pfx2 = new StringBuffer(lines[1]);
                int number2 = Integer.parseInt(pfx2.substring(pfx2.length() - 1).trim());
//                System.out.printf("%s :%s - %s \n", l, pfx1, pfx2);
                for (int i = number1; i <= number2 ; i++) {
                    String k = pfx1.substring(0, pfx1.length()-1);
                    k = 8 + k + i;
//                    System.out.printf("%s :%s - %s \n", l, k, pfx2);
                    numberPfx.add(k);
                }
            } else {
                if (l.contains("D")) numberPfx.add(l.trim());
                else {
                    l=8+l;
                    numberPfx.add(l.trim());
                }
            }
        }
    }
}
