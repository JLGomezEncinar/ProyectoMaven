package iessanalberto.dam1.proyectomaven.alerts;
import javafx.application.Platform;
import javafx.scene.control.Alert;
public class AlertClass {
    public void showAlert (Alert.AlertType alertType, String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

}
