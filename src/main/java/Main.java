import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import staticVariable.PrimaryStage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI/samples/mainSample.fxml"));
//        primaryStage.setTitle("Hello World");
        PrimaryStage.primaryStage = primaryStage;
                primaryStage.setScene(new Scene(root, 1150, 780));
                primaryStage.setResizable(false);
                primaryStage.show();
                }

public static void main(String[] args) {
        launch(args);

//    String regex = "^\\s\\d{5,}\\s";
//    String message = " 309043             0             22                  219                     65535                 10                NULL                  <NULL>             NULL                  Master";
//    message.toString();
//
//    Pattern pattern = Pattern.compile(regex);
//    Matcher m = pattern.matcher(message);
//    System.out.println(m.find());
//

        }
        }
