import SQLdialog.HolderBD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static staticVariable.PrimaryStage.primaryStage;

public class Htool extends Application {
    public static void main(String[] args) {
        new HolderBD().run();
//        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        String fxmlFile = "/fxml/hello.fxml";
//        FXMLLoader loader = new FXMLLoader();
//        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
//        stage.setTitle("JavaFX and Maven");
//        stage.setScene(new Scene(root));
//        stage.show();

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("fxml/mainSample.fxml"));
//        primaryStage.setTitle("Hello World");

        primaryStage = stage;
//        primaryStage.setScene(new Scene(root, 1150, 780));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
