package GUI.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import dialogWithCommutators.customers.Customer;
import staticVariable.StaticVariables;

public class TblCusController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView table;

    @FXML
    private TableColumn<Customer, String> number = new TableColumn<>("Number");

    @FXML
    private TableColumn<Customer, String> lp = new TableColumn<>("Lp");

    @FXML
    private TableColumn<Customer, String> tg = new TableColumn<>("TG");

    @FXML
    private TableColumn<Customer, String> rsc = new TableColumn<>("RSC");

    @FXML
    private TableColumn<Customer, String> csc = new TableColumn<>("CSC");

    @FXML
    private TableColumn<Customer, Boolean> status = new TableColumn<>("Status");

    @FXML
    private TableColumn<Customer, String> category = new TableColumn<>("Category");


    @FXML
    void initialize() {

        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        lp.setCellValueFactory(new PropertyValueFactory<>("lp"));
        tg.setCellValueFactory(new PropertyValueFactory<>("tg"));
        rsc.setCellValueFactory(new PropertyValueFactory<>("rsc"));
        csc.setCellValueFactory(new PropertyValueFactory<>("csc"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));



        ObservableList<Customer> list = getCustomerInnerList();
        table.setItems(list);

    }


    private ObservableList<Customer> getCustomerInnerList() {
        ArrayList<Customer> list = new ArrayList<>();

//        if (StaticVariables.customers.size() == 0) new ListOfServers().reading();
        System.out.println("-------------------------------------------------------------");
        for (Map.Entry<String, Customer> entry: StaticVariables.customers.entrySet()){
            list.add(entry.getValue());

//            System.out.println(entry.getValue().getNumber() + " " + entry.getValue().getLp()
//                + " " + entry.getValue().getTg() + " " + entry.getValue().getCsc() + " "
//                + entry.getValue().getRsc() + " " + entry.getValue().getCategory() + " "
//                + entry.getValue().getStatus());
        }
        ObservableList<Customer> out = FXCollections.observableArrayList(list);
        return out;
    }
}
