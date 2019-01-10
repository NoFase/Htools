package workWithFiles;

import GUI.otherWindows.MyAlert;
import SQLdialog.Holder;
import SQLdialog.HolderH2;
import SQLdialog.MessageRequest;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static staticVariable.StaticVariables.listOfServers;

public class ListOfServers {
    private ArrayList<MenuItem> itServers = new ArrayList<>();
    private ArrayList<String> servers = new ArrayList<>();
    private String abrServer, name, ip;

    public String getAbrServer() {
        return abrServer;
    }

    public void reading1(){
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

    public void writing1(String abr) throws IOException {
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

    public void writing(String abr){
        name = listOfServers.get(abr)[0];
        ip = listOfServers.get(abr)[1];

        Holder holder = new HolderH2();
        ((HolderH2) holder).connecting();
//        создаем на всякий случай таблицу серверов, если она не создана
        ((HolderH2) holder).executing(new MessageRequest().creatingTblServers());
//        выясняем максимальный Id в таблице
        int maxId = 1;
        try{
            ResultSet rs = ((HolderH2) holder).requesting(new MessageRequest().selectingMaxIdInTbl("tbl_Servers"));
            while (rs.next()) {
                maxId = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        добавляем запись в таблицу
                ((HolderH2) holder).executing(new MessageRequest().addingToTblServers(++maxId, abr, name, ip));
                new MyAlert("Сервер с названием: " + name + " и ip адресом: " + ip + " был добавлен в таблицу серверов.");
        holder.closeConnecting();
    }

    public void reading(){
        Holder holder = new HolderH2();
        ((HolderH2) holder).connecting();
        try {
            ResultSet tmpRs = holder.getConnection().getMetaData().getTables(null, null, "tbl_Servers", null);
            ResultSet rs;
            if ( tmpRs!= null) {
                tmpRs.close();
                rs = ((HolderH2) holder).requesting(new MessageRequest().listAllOfTbl("tbl_Servers"));
                while (rs.next()) {
                    listOfServers.put(rs.getString(2), new String[]{rs.getString(3), rs.getString(4)});
                }
            }
            else tmpRs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void clearing(String nameTbl){
        Holder holder = new HolderH2();
        ((HolderH2) holder).connecting();
        if (((HolderH2) holder).requesting(new MessageRequest().listAllOfTbl(nameTbl)) != null){
            ((HolderH2) holder).executing(new MessageRequest().deletingTbl(nameTbl));
        }
        holder.closeConnecting();
    }
}
