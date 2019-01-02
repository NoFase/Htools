package workWithFiles;

import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static staticVariable.StaticVariables.listOfServers;

public class ListOfServers {
    private ArrayList<MenuItem> itServers = new ArrayList<>();
    private ArrayList<String> servers = new ArrayList<>();
    private String abrServer;

    public String getAbrServer() {
        return abrServer;
    }

    public void reading(){
        try {
            ReadFromFile rf = new ReadFromFile("src\\main\\resources\\OtherFiles\\servers.txt");

            String[] lines = rf.reading().toString().split("\n");
            for (String x: lines){
                String[] partOfLine = x.split(":");
//                первая часть это название вторая это IP
                if (partOfLine.length > 1)listOfServers.put(partOfLine[0], new String[]{partOfLine[1], partOfLine[2]});
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

    public void showing(SplitMenuButton mnServers){
//        читаем из файла список серверов
        reading();
//        проверяем пустой ли список с серверами
        if (!listOfServers.isEmpty()) {

//        проходим по всему списку серверов и инициируем коллекцию servers
            for (HashMap.Entry<String, String[]> entry : listOfServers.entrySet()) {
                servers.add(entry.getKey());
            }
//        если списсок MenuItem пустой инициализируем его с абривиатур серверов
            if (itServers.size() == 0) {
                for (int i = 0; i < servers.size(); i++) {
                    itServers.add(new MenuItem(servers.get(i)));
//        добавляем MenuItem в SplitMenuButton
                    mnServers.getItems().add(itServers.get(i));
//        привязываем сразу к каждому MenuItem Action
                    itServers.get(i).setOnAction(event1 -> {
//        для того чтобы взять текст с текущего MenuItem нужно создать его еще раз и присвоить иему значения из эвента
                        MenuItem item = (MenuItem) event1.getTarget();
//                        spMenuBut.setText(item.getText());
                        abrServer = item.getText();
                        mnServers.setText(listOfServers.get(abrServer)[0]);
                    });
                }
            }
        }
    }
}
