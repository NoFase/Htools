import SQLdialog.Holder;
import SQLdialog.HolderH2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static staticVariable.PrimaryStage.primaryStage;

public class Htool extends Application {
    public static void main(String[] args) {
        launch(args);

//        HolderH2 hh = new HolderH2();

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("fxml/mainSample.fxml"));
        primaryStage = stage;
//        primaryStage.setScene(new Scene(root, 1150, 780));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
