package GUI.controllers;

import GUI.otherWindows.MyAlert;
import GUI.otherWindows.TextForHelps;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import logic.AnalizerForSQL;
import workWithFiles.ListOfServers;

import java.io.IOException;

import static staticVariable.StaticVariables.customers;
import static staticVariable.StaticVariables.listOfServers;

public class WorkWithUserController extends MainController {

    ListOfServers listServers;

    @FXML
    private SplitMenuButton mnServers;
    @FXML
    private CheckBox chkPra, chkSip, chkEsl, chkPrk, chkCSC, chkCus, chkCharg, chkTg, chkRsc, chkAllUsr, chkAllSettings;
    @FXML
    private Button btnRequestData;
    @FXML
    private TextField fldCSC, fldCus, fldTg, fldRsc;

    @FXML
    public void appServers(ActionEvent event){
        listServers = new ListOfServers();
        listServers.showing(mnServers);
    }

    @FXML
    void appBtnRequest(ActionEvent event) {
        customers.clear();
        if (mnServers.getText().equals("Выбор сервера")) new MyAlert("Вы не выбрали сервер!");
        else {
            if (!chkPra.isSelected() && !chkSip.isSelected() && !chkEsl.isSelected())
            {
                new MyAlert("Нужно выбрать тип абонента!");
                return;
            }
            else {
                if (chkAllSettings.isSelected() && chkAllUsr.isSelected()) {
//      выбор когда зажаты обе галки all
                    new AnalizerForSQL(listOfServers.get(listServers.getAbrServer())[1], chkPra.isSelected(), chkSip.isSelected(), chkEsl.isSelected(),
                            chkPrk.isSelected(), chkCSC.isSelected(), chkCus.isSelected(), chkCharg.isSelected(),
                            chkTg.isSelected(), chkRsc.isSelected(),
                            fldCSC.getText(), fldCus.getText(), fldTg.getText(), fldRsc.getText());
//      выбор когда зажаты все галки
                } else if (chkPra.isSelected() && chkSip.isSelected() && chkEsl.isSelected() &&
                        chkPrk.isSelected() && chkCSC.isSelected() && chkCus.isSelected() && chkCharg.isSelected() &&
                        chkTg.isSelected() && chkRsc.isSelected()) {
                    new AnalizerForSQL(listOfServers.get(listServers.getAbrServer())[1], chkPra.isSelected(), chkSip.isSelected(), chkEsl.isSelected(),
                            chkPrk.isSelected(), chkCSC.isSelected(), chkCus.isSelected(), chkCharg.isSelected(),
                            chkTg.isSelected(), chkRsc.isSelected(),
                            fldCSC.getText(), fldCus.getText(), fldTg.getText(), fldRsc.getText());
//       выбор когда зажаты только все галки абонентов
                } else if (chkPra.isSelected() && chkSip.isSelected() && chkEsl.isSelected()) {
                    new AnalizerForSQL(listOfServers.get(listServers.getAbrServer())[1], chkPra.isSelected(), chkSip.isSelected(), chkEsl.isSelected(),
                            chkPrk.isSelected(), chkCSC.isSelected(), chkCus.isSelected(), chkCharg.isSelected(),
                            chkTg.isSelected(), chkRsc.isSelected(),
                            fldCSC.getText(), fldCus.getText(), fldTg.getText(), fldRsc.getText());
//       выбор когда нажата галка только all в абонентах
                } else if (chkAllUsr.isSelected()) {
                    new AnalizerForSQL(listOfServers.get(listServers.getAbrServer())[1], chkPra.isSelected(), chkSip.isSelected(), chkEsl.isSelected(),
                            chkPrk.isSelected(), chkCSC.isSelected(), chkCus.isSelected(), chkCharg.isSelected(),
                            chkTg.isSelected(), chkRsc.isSelected(),
                            fldCSC.getText(), fldCus.getText(), fldTg.getText(), fldRsc.getText());
//       выбор только PRA и SIP абонентов
                } else if (chkPra.isSelected() && chkSip.isSelected()) {
                    new AnalizerForSQL(listOfServers.get(listServers.getAbrServer())[1], chkPra.isSelected(), chkSip.isSelected(),
                            chkPrk.isSelected(), chkCSC.isSelected(), chkCus.isSelected(), chkCharg.isSelected(),
                            chkTg.isSelected(), chkRsc.isSelected(),
                            fldCSC.getText(), fldCus.getText(), fldTg.getText(), fldRsc.getText());
//      выбор только PRA и ESL абонентов
                } else if (chkPra.isSelected() && chkEsl.isSelected()) {
                    new AnalizerForSQL(listOfServers.get(listServers.getAbrServer())[1], chkPra.isSelected(), chkEsl.isSelected(),
                            chkPrk.isSelected(), chkCSC.isSelected(), chkCus.isSelected(), chkCharg.isSelected(),
                            chkTg.isSelected(), chkRsc.isSelected(),
                            fldCSC.getText(), fldCus.getText(), fldTg.getText(), fldRsc.getText(), 1);
//      выбор только SIP и ESL абонентов
                } else if (chkSip.isSelected() && chkEsl.isSelected()) {
                    new AnalizerForSQL(listOfServers.get(listServers.getAbrServer())[1], chkSip.isSelected(), chkEsl.isSelected(),
                            chkPrk.isSelected(), chkCSC.isSelected(), chkCus.isSelected(), chkCharg.isSelected(),
                            fldCSC.getText(), fldCus.getText());
//      выбрана только галка на PRA абонентах
                } else if (chkPra.isSelected()) new AnalizerForSQL(listOfServers.get(listServers.getAbrServer())[1],
                        chkPra.isSelected(), chkPrk.isSelected(), chkCSC.isSelected(), chkCus.isSelected(),
                        chkCharg.isSelected(), chkTg.isSelected(), chkRsc.isSelected(), fldCSC.getText(), fldCus.getText(), fldTg.getText(),
                        fldRsc.getText());
//      выбрана только галка на Sip абонентах
                else if (chkSip.isSelected()) new AnalizerForSQL(listOfServers.get(listServers.getAbrServer())[1],
                        chkPrk.isSelected(), chkCSC.isSelected(), chkCus.isSelected(),
                        chkCharg.isSelected(), fldCSC.getText(), fldCus.getText(), 1);
//      выбрана только галка на ESL абонентах
                else if (chkEsl.isSelected()) new AnalizerForSQL(listOfServers.get(listServers.getAbrServer())[1],
                        chkPrk.isSelected(), chkCSC.isSelected(), chkCus.isSelected(),
                        chkCharg.isSelected(), fldCSC.getText(), fldCus.getText(), 0);
            }
        }
        showTables();
    }

    private void showTables() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/tblCustomer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
//        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void initialize() {
//        Set helps for fields
        fldTg.setTooltip(new Tooltip(new TextForHelps().getFldTgWorkWithUsr()));
        fldRsc.setTooltip(new Tooltip(new TextForHelps().getFldRscWorkWithUsr()));
        fldCus.setTooltip(new Tooltip(new TextForHelps().getFldCusWorkWithUsr()));


        chkAllUsr.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (chkAllUsr.isSelected()) {
                    chkPra.setSelected(true);
                    chkEsl.setSelected(true);
                    chkSip.setSelected(true);
                } else {
                    chkPra.setSelected(false);
                    chkEsl.setSelected(false);
                    chkSip.setSelected(false);
                }
            }
        });
        chkPra.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (chkPra.isSelected()) {
                    chkTg.setDisable(false);
                    chkRsc.setDisable(false);
                    fldTg.setDisable(false);
                    fldRsc.setDisable(false);
                } else {
                    chkTg.setDisable(true);
                    chkRsc.setDisable(true);
                    fldTg.setDisable(true);
                    fldRsc.setDisable(true);

                }
            }
        });

        chkAllSettings.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (chkAllSettings.isSelected()){
                    chkPrk.setSelected(true);
                    chkCSC.setSelected(true);
                    chkCus.setSelected(true);
                    chkCharg.setSelected(true);
                    if (chkPra.isSelected()) {
                        chkTg.setSelected(true);
                        chkRsc.setSelected(true);
                    }
                } else {
                    chkPrk.setSelected(false);
                    chkCSC.setSelected(false);
                    chkCus.setSelected(false);
                    chkCharg.setSelected(false);
                    chkTg.setSelected(false);
                    chkRsc.setSelected(false);
                }
            }
        });
        chkTg.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (chkTg.isSelected()) {
                    chkRsc.setSelected(false);
                    chkRsc.setDisable(true);
                    fldRsc.setText("");
                    fldRsc.setDisable(true);
                } else {
                    chkRsc.setDisable(false);
                    fldRsc.setDisable(false);
                }
            }
        });
        chkRsc.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (chkRsc.isSelected()){
                    chkTg.setSelected(false);
                    chkTg.setDisable(true);
                    fldTg.setText("");
                    fldTg.setDisable(true);
                } else {
                    chkTg.setDisable(false);
                    fldTg.setDisable(false);
                }
            }
        });
    }
}

