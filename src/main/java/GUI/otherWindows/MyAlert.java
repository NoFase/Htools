package GUI.otherWindows;

import javafx.scene.control.Alert;

public class MyAlert {
    private String message;
    final private String TITLE = "что то пошло не так...";

    public MyAlert(String message) {
        this.message = message;

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(TITLE);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
