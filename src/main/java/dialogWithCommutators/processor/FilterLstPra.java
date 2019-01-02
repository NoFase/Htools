package dialogWithCommutators.processor;


import dialogWithCommutators.customers.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static staticVariable.StaticVariables.*;

public class FilterLstPra {
    private String[] line;
    private int totalPra = 0;
//    private String actualNumber = null;

    public FilterLstPra(String message) {
        String regex = "^\\s\\d{5,}\\s";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(message);
        if (m.find()) {
            line = message.split("\\s+");
            customers.put(line[1], new CustomerPra(line[1], line[2], line[4], line[5],line[6]));
        }
        else if (message.contains("Number of results =")){
            String[] lex = message.split("=");
            StringBuffer l = new StringBuffer(lex[1].trim());
            l.deleteCharAt(l.length()-1);
            totalPra = Integer.parseInt(l.toString());

//            fldPlace.appendText("Количество PRA абонентов: " + totalPra + "\n");
            isCommands.put("LSTPRA", false);
        }
        else if (message.contains("No matching result is found")) {
            isCommands.put("LSTPRA", false);
            fldPlace.appendText("PRA абонентов на коммутаторе нет!\n");
        }
//        else System.out.println(message);
    }

    public FilterLstPra(String message, boolean x){
//        System.out.println(actualNumber + " --- " + increment);
//        fldPlace.appendText(actualNumber + " --- " + increment);
//        fldPlace.setText(actualNumber + " - " + increment);
        if (message.contains("Subscriber number")) {
            actualNumber = message.split("=")[1].trim();
            increment++;
        }
//        else if (message.contains("Number of results =")) {
////            actualNumber = null;
//            isCommands.put("LSTPRAx", false);
//        }
        else if (message.contains("Subscriber status") && actualNumber != null){
//            System.out.println(message.split("=")[1].trim());
            if(message.split("=")[1].trim().contains("Normal")) {
                customers.get(actualNumber).setStatus("1");
//                System.out.println(actualNumber + " - " + customers.get(actualNumber).getStatus());
            }
            else customers.get(actualNumber).setStatus("0");
        }
        else if (message.contains("Custom subscriber type") && actualNumber != null) {
            customers.get(actualNumber).setCategory(message.split("=")[1]);
//            System.out.println(message.split("=")[1]);
        }
    }


}
