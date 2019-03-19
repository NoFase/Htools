package GUI.controllers;

import GUI.otherWindows.MyAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import workWithFiles.ListOfServers;
import staticVariable.StaticVariables;

import java.io.IOException;
import java.util.HashMap;

public class AddingController extends MainController{

    @FXML
    private TextField fldNameServer;

    @FXML
    private TextField fldABR;

    @FXML
    private TextField fldIp;

    @FXML
    void appAdding(ActionEvent event) throws IOException {

        if (StaticVariables.listOfServers.size() > 0){
            for (HashMap.Entry<String, String[]> server : StaticVariables.listOfServers.entrySet()){
                if (server.getKey().contains(fldABR.getText()) || server.getValue()[1].contains(fldIp.getText()) ||
                server.getValue()[0].equals(fldNameServer.getText())) {
                    new MyAlert("такой сервер уже добавлен");
                    return;
                }
            }
        }
        ListOfServers servers = new ListOfServers();
        servers.reading();
        StaticVariables.listOfServers.put(fldABR.getText(), new String[]{fldNameServer.getText(), fldIp.getText()});
        servers.writing(fldABR.getText());
//            reOpenNewWindow("fxml/mainSample.fxml");
    }

    @FXML
    void appAddingServer(ActionEvent event) {
    }


    @FXML
    void initialize() {

    }
}
