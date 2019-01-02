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
        new ListOfServers().showing(spMenuBut);
    }

    @FXML
    void initialize() {

    }
}
