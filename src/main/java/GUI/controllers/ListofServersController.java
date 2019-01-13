package GUI.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import staticVariable.StaticVariables;
import workWithFiles.ListOfServers;

public class ListofServersController {


    @FXML
    private AnchorPane panelListOfServers;

    @FXML
    private TableView table;

    @FXML
    void appClear(ActionEvent event) {
        System.out.println("===> ListofServersController ---> method appClear --> event");
        ListOfServers servers = new ListOfServers();
        servers.clearing("tbl_Servers");
        StaticVariables.listOfServers.clear();
        panelListOfServers.getScene().getWindow().hide();
        System.out.println("===> ListofServersController ---> method appClear --> hide Scene");
    }

    @FXML
    private TableColumn<ServerInner, String> tblAbr = new TableColumn<>("ABR");

    @FXML
    private TableColumn<ServerInner, String>  tblNameServer = new TableColumn<>("Server Name");

    @FXML
    private TableColumn<ServerInner, String>  tblIp = new TableColumn<>("Ip address");

    @FXML
    void initialize() {

//        при заполнении таблиц String в PropertyValueFactory должно строго соотвествовать String в
//        классе из которого вытаскиваем данные
        tblAbr.setCellValueFactory(new PropertyValueFactory<>("abr"));
        tblNameServer.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblIp.setCellValueFactory(new PropertyValueFactory<>("ip"));

        ObservableList<ServerInner> list = getServerinnerList();
        table.setItems(list);
        System.out.println("===> ListofServersController ---> method initialize --> finish initialize");
    }

    private ObservableList<ServerInner> getServerinnerList() {
        ArrayList<ServerInner> list = new ArrayList<>();

        if (StaticVariables.listOfServers.size() == 0) new ListOfServers().reading();

        for (Map.Entry<String, String[]> entry: StaticVariables.listOfServers.entrySet()){
            list.add(new ServerInner(entry.getKey(),entry.getValue()[0], entry.getValue()[1]));
        }
        ObservableList<ServerInner> out = FXCollections.observableArrayList(list);
        return out;
    }


}

