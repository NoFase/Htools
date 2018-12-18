package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import workWithFiles.ListOfServers;
import staticVariable.StaticVariables;

import java.io.IOException;

public class AddingController extends MainController{

    @FXML
    private TextField fldNameServer;

    @FXML
    private TextField fldABR;

    @FXML
    private TextField fldIp;

    @FXML
    void appAdding(ActionEvent event) throws IOException {
        if (!StaticVariables.listOfServers.containsKey(fldABR.getText())) {
            StaticVariables.listOfServers.put(fldABR.getText(), new String[]{fldNameServer.getText(), fldIp.getText()});
            new ListOfServers().writing(fldABR.getText());

            reOpenNewWindow("fxml/mainSample.fxml");
        } else {
            System.out.println("такой сервер уже добавлен");
        }
    }

    @FXML
    void appAddingServer(ActionEvent event) {
    }


    @FXML
    void initialize() {

    }
}
