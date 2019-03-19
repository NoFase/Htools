package GUI;

import SQLdialog.HolderH2;
import SQLdialog.MessageRequest;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class Shema extends Application {
    @Override public void start(Stage stage){
        NumberAxis xAxis = new NumberAxis(1, 20, 10000000);
        NumberAxis yAxis = new NumberAxis(0, 30, 5);
        CategoryAxis xDate = new CategoryAxis();
        xDate.autosize();

        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm");

//        AreaChart<Number, Number> aChart = new AreaChart<>(xAxis, yAxis);
        AreaChart<String, Number> aChart = new AreaChart<String, Number>(xDate, yAxis);

//        XYChart.Series<Number, Number> ser1 = new XYChart.Series();
        XYChart.Series ser1 = new XYChart.Series();

        HolderH2 holder = new HolderH2();
        holder.connecting();
        ResultSet rs;
        Date date = new Date();
        try {
            rs = holder.requesting(new MessageRequest().listAllOfTbl("tblSRTTG19"));

            while (rs.next()) {
//                date.setTime(rs.getLong(2));

//                if (rs.getInt(1) > 3) {
                    date.setTime(rs.getLong(2));
                    long x = rs.getLong(2);
//                    if (xAxis.getLowerBound() == 1) {
//                        xAxis.setLowerBound(x);
//                        xAxis.setUpperBound(x + 20000000);
//                    }
                    int y = Integer.parseInt(rs.getString(5));
                    ser1.getData().add(new XYChart.Data(form.format(date), y));
//                }
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(aChart,400, 500);
        aChart.getData().add(ser1);
        stage.setScene(scene);
        stage.show();
    }
}
