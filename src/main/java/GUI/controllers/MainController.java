package GUI.controllers;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import staticVariable.PrimaryStage;

import static staticVariable.StaticVariables.connection;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainPane;

    @FXML
    MenuItem itConnect;

    @FXML
    MenuItem itReconnect;

    @FXML
    MenuItem itDisconnect;

    @FXML
    MenuItem itShowUsr;

    @FXML
    void appShowUsr(ActionEvent event){}

    @FXML
    void appReconnecting(ActionEvent event) {}

    @FXML
    void appListOfServers(ActionEvent event){
        try {
            FXMLDocumentController(new Stage(), "fxml/listOfServers.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void appAddingServer(ActionEvent event) throws IOException {
        reOpenNewWindow("fxml/addingSample.fxml");
    }

    @FXML
    void appClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void appConnectingServer(ActionEvent event) throws IOException {
        reOpenNewWindow("fxml/connectingSample.fxml");
    }

    @FXML
    void appDisconnectingServer(ActionEvent event) {
        connection.disconnect();
    }

    @FXML
    void initialize() {
    }

    protected void FXMLDocumentController(Stage stage, String path) throws IOException {
        //Загрузили ресурс файла
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(path));
        Scene scene = new Scene(root);
//        Scene scene = new Scene(root, 1150, 780);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    protected void reOpenNewWindow (String path) {
        PrimaryStage.primaryStage.hide();
        //графический контейнер
        Stage stage = PrimaryStage.primaryStage;
        try {
            FXMLDocumentController(stage, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
