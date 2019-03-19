package GUI.controllers;

import SQLdialog.HolderH2;
import SQLdialog.MessageRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GraphController extends MainController {

    private ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();

    @FXML
    private NumberAxis lnX;

//    @FXML
//    private NumberAxis lnY;

    @FXML
    private AreaChart aChart;

    @FXML
    void initialize() {
        XYChart.Series<Number, Number> ser1 = new XYChart.Series();
//        XYChart.Series<Number, Number> ser2 = new XYChart.Series<Number, Number>();

//        ObservableList<XYChart> data1 = FXCollections.observableArrayList();
//        ObservableList<XYChart> data2 = FXCollections.observableArrayList();

        HolderH2 holder = new HolderH2();
        holder.connecting();
        ResultSet rs;
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
//        aChart.getXAxis().
        try {
            rs = holder.requesting(new MessageRequest().listAllOfTbl("tblSRTTG19"));
            int i = 0;
            while (rs.next()) {

                date.setTime(rs.getLong(2));
                int x = rs.getInt(1);
                int y = Integer.parseInt(rs.getString(5));
//                System.out.println(x + " " + y);
//                if (lnX.getLayoutX() == 0.0) lnX.setLayoutX(x);

                ser1.getData().add(new XYChart.Data<Number, Number>(x, y));
//                System.out.println(aChart.getLayoutX());
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }



        aChart.getData().add(ser1);



    }
}
