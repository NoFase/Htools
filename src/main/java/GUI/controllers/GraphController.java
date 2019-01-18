package GUI.controllers;

import SQLdialog.HolderH2;
import SQLdialog.MessageRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class GraphController extends MainController {
    @FXML
    private NumberAxis lnY;

    @FXML
    private NumberAxis lnX;

    @FXML
    private AreaChart aChart;

    @FXML
    void initialize() {
        XYChart.Series<Date, Number> ser1 = new XYChart.Series<Date, Number>();
//        XYChart.Series<Number, Number> ser2 = new XYChart.Series<Number, Number>();

//        ObservableList<XYChart> data1 = FXCollections.observableArrayList();
//        ObservableList<XYChart> data2 = FXCollections.observableArrayList();

        HolderH2 holder = new HolderH2();
        holder.connecting();
        ResultSet rs;
        try {
            rs = holder.requesting(new MessageRequest().listAllOfTbl("tblSRTTG19"));
            int i = 0;
            while (rs.next()) {
                long time = rs.getLong(2);
                Date date = new Date();
                String tt = String.format("%02d:%02d:%02d", time / 1000 / 3600, time / 1000 / 60 % 60, time / 1000 % 60);
                System.out.println(tt);
                int y = Integer.parseInt(rs.getString(5));
//                System.out.println(x + " " + y);
                ser1.getData().add(new XYChart.Data<Date, Number>(new Date(tt), y));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        aChart.getData().add(ser1);
    }
}
