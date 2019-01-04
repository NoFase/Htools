package GUI.controllers;

import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import dialogWithCommutators.customers.Customer;
import javafx.scene.input.*;
import staticVariable.StaticVariables;

public class TblCusController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView table;

    @FXML
    private TableColumn<Customer, String> TYPE = new TableColumn<>("Type subscriber");

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
    private TableColumn<Customer, Boolean> intraOfficeI = new TableColumn<>("intraOfficeI");

   @FXML
    private TableColumn<Customer, Boolean> localI = new TableColumn<>("localI");

   @FXML
    private TableColumn<Customer, Boolean> localTollI = new TableColumn<>("localTollI");

   @FXML
    private TableColumn<Customer, Boolean> nationalI = new TableColumn<>("nationalI");

   @FXML
    private TableColumn<Customer, Boolean> internationalI = new TableColumn<>("internationalI");

   @FXML
    private TableColumn<Customer, Boolean> intraOfficeO = new TableColumn<>("intraOfficeO");

   @FXML
    private TableColumn<Customer, Boolean> localO = new TableColumn<>("localO");

   @FXML
    private TableColumn<Customer, Boolean> localTollO = new TableColumn<>("localTollO");

   @FXML
    private TableColumn<Customer, Boolean> nationalO = new TableColumn<>("nationalO");

   @FXML
    private TableColumn<Customer, Boolean> internationalO = new TableColumn<>("internationalO");



    @FXML
    void initialize() {

        TYPE.setCellValueFactory(new PropertyValueFactory<>("TYPE"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        lp.setCellValueFactory(new PropertyValueFactory<>("lp"));
        tg.setCellValueFactory(new PropertyValueFactory<>("tg"));
        rsc.setCellValueFactory(new PropertyValueFactory<>("rsc"));
        csc.setCellValueFactory(new PropertyValueFactory<>("csc"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));

        intraOfficeI.setCellValueFactory(new PropertyValueFactory<>("intraOfficeI"));
        localI.setCellValueFactory(new PropertyValueFactory<>("localI"));
        localTollI.setCellValueFactory(new PropertyValueFactory<>("localTollI"));
        nationalI.setCellValueFactory(new PropertyValueFactory<>("nationalI"));
        internationalI.setCellValueFactory(new PropertyValueFactory<>("internationalI"));
        intraOfficeO.setCellValueFactory(new PropertyValueFactory<>("intraOfficeO"));
        localO.setCellValueFactory(new PropertyValueFactory<>("localO"));
        localTollO.setCellValueFactory(new PropertyValueFactory<>("localTollO"));
        nationalO.setCellValueFactory(new PropertyValueFactory<>("nationalO"));
        internationalO.setCellValueFactory(new PropertyValueFactory<>("internationalO"));

        ObservableList<Customer> list = getCustomerInnerList();
        table.setItems(list);
//        устанавливаем мультивыделение в таблице
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        copySelectionToClipboard(table);
    }


    private ObservableList<Customer> getCustomerInnerList() {
        ArrayList<Customer> list = new ArrayList<>();

//        System.out.println("-------------------------------------------------------------");
        for (Map.Entry<String, Customer> entry: StaticVariables.customers.entrySet()){
            list.add(entry.getValue());
        }
        ObservableList<Customer> out = FXCollections.observableArrayList(list);
        return out;
    }

    @SuppressWarnings("rawtypes")
//    метод для копирования в буфер
    public void copySelectionToClipboard(final TableView<?> table) {
        final Set<Integer> rows = new TreeSet<>();
        for (final TablePosition tablePosition : table.getSelectionModel().getSelectedCells()) {
            rows.add(tablePosition.getRow());
        }
        final StringBuilder strb = new StringBuilder();
        boolean firstRow = true;
        for (final Integer row : rows) {
            if (!firstRow) {
                strb.append('\n');
            }
            firstRow = false;
            boolean firstCol = true;
            for (final TableColumn<?, ?> column : table.getColumns()) {
                if (!firstCol) {
                    strb.append('\t');
                }
                firstCol = false;
                final Object cellData = column.getCellData(row);
                strb.append(cellData == null ? "" : cellData.toString());
            }
        }
//      дополнительно, копирование через CTR+C
        final KeyCodeCombination keyCodeCopy = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_ANY);
        table.setOnKeyPressed(event -> {
            if (keyCodeCopy.match(event)) {
                copySelectionToClipboard(table);
            }
        });
        final ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(strb.toString());
        Clipboard.getSystemClipboard().setContent(clipboardContent);
    }
}
