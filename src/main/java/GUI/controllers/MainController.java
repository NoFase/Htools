package GUI.controllers;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import GUI.Shema;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pfxAnalays.CnacldConnect;
import pfxAnalays.DefaultConnection;
import pfxAnalays.RSCConnect;
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
    void workWithUser(ActionEvent event){
        System.out.println("===> MainController ---> method workWithUser --> event");
        reOpenNewWindow("fxml/workWithUser.fxml");
//        try {
//            FXMLDocumentController(new Stage(), "fxml/workWithUser.fxml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    void appReconnecting(ActionEvent event) {}

    @FXML
    void appListOfServers(ActionEvent event){
        System.out.println("===> MainController ---> method appListOfServers --> event");
        try {
            FXMLDocumentController(new Stage(), "fxml/listOfServers.fxml");
            System.out.println("===> MainController ---> method appListOfServers --> load fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void appAddingServer(ActionEvent event) throws IOException {
        System.out.println("===> MainController ---> method appAddingServer --> event");
        reOpenNewWindow("fxml/addingSample.fxml");
    }

    @FXML
    void appClose(ActionEvent event) {
        System.out.println("===> MainController ---> method appClose --> event EXIT");
        System.exit(0);
    }

    @FXML
    void appConnectingServer(ActionEvent event) throws IOException {
        System.out.println("===> MainController ---> method appConnectingServer --> event");
        reOpenNewWindow("fxml/connectingSample.fxml");
    }

    @FXML
    void appDisconnectingServer(ActionEvent event) {
        System.out.println("===> MainController ---> method appDisconnectingServer --> event DISCONNECT");
        connection.disconnect();
    }

    @FXML
    void appAnalPfx(ActionEvent event){
//        Siren siren = new Siren();
        DefaultConnection connect;
        connect = new CnacldConnect();
        connect.run();
        while (connect.isAlive()) {}
        System.out.println("===> MainController ---> method appAnalPfx --> finish collect CNACLD");
        connect = new RSCConnect();
    }

    @FXML
    void initialize() {
    }

    protected void FXMLDocumentController(Stage stage, String path) throws IOException {
        //Загрузили ресурс файла
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(path));
        System.out.println("===> MainController ---> method FXMLDocumentController --> load fxml:" + path);
        Scene scene = new Scene(root);
//        Scene scene = new Scene(root, 1150, 780);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        System.out.println("===> MainController ---> method FXMLDocumentController --> show Stage");
    }

    protected void reOpenNewWindow (String path) {
        System.out.println("===> MainController ---> method reOpenNewWindow --> hide PrimaryStage");
        PrimaryStage.primaryStage.hide();
        //графический контейнер
        Stage stage = PrimaryStage.primaryStage;
        try {
            FXMLDocumentController(stage, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void appShowGraph(ActionEvent event) {
        System.out.println("===> MainController ---> method appShowGraph --> event");
//        reOpenNewWindow("src/main/resources/fxml/graphSample.fxml");
//        try {
//            FXMLDocumentController(new Stage(), "fxml/graphSample.fxml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        new Shema().start(new Stage());
    }

    @FXML
    private MenuItem itShowGraph;
}
