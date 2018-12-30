package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import workWithFiles.ListOfServers;

import java.util.ArrayList;
import java.util.HashMap;

import static staticVariable.StaticVariables.listOfServers;

public class WorkWithUserController extends MainController {

    private ArrayList<MenuItem> itServers = new ArrayList<>();
    private ArrayList<String> servers = new ArrayList<>();
    private String abrServer;

    @FXML
    private SplitMenuButton mnServers;
    @FXML
    private CheckBox chkPra, chkSip, chkEsl, chkPrk, chkRestrict, chkCus, chkCharg, chkTg, chkRsc, chkGw, chkInterface, chkAllUsr, chkAllSettings;
    @FXML
    private Button btnRequestData;
    @FXML
    private TextField fldCus, fldTg, fldRsc, fldGw, fldInterface;

    @FXML
    public void appServers(ActionEvent event){
        //        читаем из файла список серверов
        new ListOfServers().reading();
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



    @FXML
    void initialize() {}
}

