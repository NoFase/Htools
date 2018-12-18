package GUI.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import staticVariable.StaticVariables;
import workWithFiles.ListOfServers;

public class ListofServersController {

    private String abbbbr = "abr";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView table;

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

