package dialogWithCommutators.processor;

import com.sun.javafx.application.PlatformImpl;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import dialogWithCommutators.commands.LstPra;
import dialogWithCommutators.customers.Customer;

import java.io.IOException;
import java.util.Map;

import static staticVariable.StaticVariables.*;

public class ShowUSR {
    public ShowUSR(TextArea fld) throws InterruptedException {
        fldPlace = fld;
        isCommands.put("LSTPRA", true);
//        new Thread(() ->{
            fldPlace.setText("how many pra subscribers?");
//        }).start();
//        fldPlace.appendText("how many pra subscribers?");

        connection.sendString(new LstPra().creatingCommand());
//        Thread.sleep(1000);
        while (isCommands.get("LSTPRA"));
//        дальше запускаааем следующие команды, но нужно добавить в анализинг проверку на конец и переключать счетчик isCommands
        new Thread(() -> {
        Platform.runLater(() -> fldPlace.setText("Total Pra subscriber: " + customers.size() + "\nplease wait..."));
        }).start();

            for (Map.Entry<String, Customer> customer: customers.entrySet()) {
                connection.sendString(new LstPra(customer.getKey(), customer.getValue().getLp()).creatingCommand());
//            actualNumber = customer.getKey();
//            System.out.println(actualNumber);
//            fldPlace.setText("Please wait...");
            }
//        while (isCommands.get("LSTPRAx"));
//        while (increment < customers.size()) {
//            System.out.println(increment);
//        }
//        increment = 1;


//        Thread thread = new Thread(() -> {
        Platform.runLater(()-> fldPlace.setText("finished, look at the tables..."));
//            Stage stage = new Stage();
//            Parent root = null;
//            try {
//                root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/GUI/samples/tblCustomer.fxml"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.setResizable(false);
//            stage.show();
//        });
//        thread.start();
    }
}
