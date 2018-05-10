package Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NotificationController {

    @FXML
    private Button okButton;

    public void okButtonClicked(){
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}
