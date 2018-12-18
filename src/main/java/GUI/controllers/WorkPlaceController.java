package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import GUI.otherWindows.MyAlert;
import dialogWithCommutators.commands.LGI;
import dialogWithCommutators.commands.LstTg;
import dialogWithCommutators.processor.FilterMessagesFromServer;
import dialogWithCommutators.processor.*;
import network.TCPConnection;
import network.TCPConnectionListener;
import staticVariable.StaticVariables;


import java.io.IOException;

import static staticVariable.StaticVariables.*;

public class WorkPlaceController extends MainController implements TCPConnectionListener {

    final private String INS = "\n\t\t* * * * *\n";
    private Boolean isConnection = false;

    @FXML
    private TextArea fldWorkPlace;

    @FXML
    private TextArea fldWorkLog;

    @FXML
    void appReconnecting(ActionEvent event) {
        System.out.println(connection.toString());
        if (isConnection) {
            new MyAlert("Вы все еще подключены к серверу!");

        } else {
            try {
                connection = new TCPConnection(this, ipServer, 6000);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void appShowUsr(ActionEvent event){
            Thread thread = new Thread(()-> {
                try {
                    new ShowUSR(fldWorkPlace);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
//    connection.sendString(new LstTg().creatingCommand());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (increment < customers.size()) {
            System.out.println(increment);
//            new Thread(()-> {
//                fldWorkLog.setText("Please wait");
//
//            });
        }

        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/tblCustomer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    @FXML
    void initialize() {
        try {
            connection = new TCPConnection(this, ipServer, 6000);
//            out = new ObjectOutputStream(new FileOutputStream("outObject.dat"));

        } catch (IOException e) {}
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        itDisconnect.setDisable(false);
        itReconnect.setDisable(true);
        itConnect.setDisable(true);
        itShowUsr.setDisable(false);
        isConnection = true;
        System.out.println("Connection ready to IP: " + ipServer);
//        fldWorkLog.setStyle("-fx-text-fill: blue ;");
        fldWorkLog.appendText("Connection ready to IP: " + ipServer + INS);

        connection.sendString(new LGI().creatingCommand());
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
//        fldWorkPlace.appendText(value + "\n");
//        System.out.println(value);
        FilterMessagesFromServer filter = new FilterMessagesFromServer(value);
        if (filter.status()) fldWorkLog.appendText(value + INS);
        else filter.choiceAnalysis();
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        itConnect.setDisable(false);
        itReconnect.setDisable(false);
        itDisconnect.setDisable(true);
        itShowUsr.setDisable(true);
        isConnection = false;
        System.out.println("Disconnect from ip: " + ipServer);
//        fldWorkLog.setStyle("-fx-text-fill: blue ;");
        fldWorkLog.appendText("Disconnect from ip: " + ipServer + INS);
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {

    }
}
