package GUI.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import GUI.otherWindows.MyAlert;
import workWithFiles.ListOfServers;

import java.util.ArrayList;
import java.util.HashMap;

import static staticVariable.StaticVariables.*;


public class ConnectingController extends MainController{

    private ArrayList<MenuItem> itServers = new ArrayList<>();
    private ArrayList<String> servers = new ArrayList<>();
    private String abrServer;

    public ConnectingController() {
    }

    @FXML
    void appConnectingServer(ActionEvent event) {
        System.out.println("!");
    }

    @FXML
    private TextField fldLogin;

    @FXML
    private PasswordField fldPwd;

    @FXML
    void appConnecting(ActionEvent event) {
        if (fldLogin.getText().equals("") || fldLogin.getText() == null || fldPwd.getText().equals("") || fldPwd.getText() == null){
            new MyAlert("Нет данных в поле Логин или Password!");
        }
        else if (spMenuBut.getText().equals("название сервера")) new MyAlert("Не выбран сервер!");
        else {
//      инициализируем данные для соединения с сервером
            loginName = fldLogin.getText();
            passwordForLogin  = fldPwd.getText();
            ipServer = listOfServers.get(abrServer)[1];
//            System.out.println(loginName + "\n" + passwordForLogin + "\n" + ipServer);
//      открываем диалоговое окно с сервером
                reOpenNewWindow("fxml/workPlaceSample.fxml");
        }

    }

    @FXML
    SplitMenuButton spMenuBut;

    @FXML
    void appSplitMenuBuuton(ActionEvent event) {
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
                    spMenuBut.getItems().add(itServers.get(i));
//        привязываем сразу к каждому MenuItem Action
                    itServers.get(i).setOnAction(event1 -> {
//        для того чтобы взять текст с текущего MenuItem нужно создать его еще раз и присвоить иему значения из эвента
                        MenuItem item = (MenuItem) event1.getTarget();
//                        spMenuBut.setText(item.getText());
                        abrServer = item.getText();
                        spMenuBut.setText(listOfServers.get(abrServer)[0]);
                    });
                }
            }
        }
    }

    @FXML
    void initialize() {

    }
}
