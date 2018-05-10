package Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AddCustomMembershipController implements Initializable{

    @FXML
    private Button enterButton;
    @FXML
    private TextField priceInput;
    @FXML
    private TextField nameOfMembership;
    @FXML
    private TextField timeframe;
    @FXML
    private Label errorLabel;
    @FXML
    private Button libraryButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button backButton;
    @FXML
    private Text time;

    public void enterButtonClicked() throws IOException, URISyntaxException {
        try {
            if (nameOfMembership.getText().equals("")) {
                errorLabel.setText("Please enter all the details");
            } else {
                String path = String.valueOf((new File(AddCustomMembershipController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())));
                String[] words = path.split("\\\\");
                String str = "";
                int diff;
                if (path.contains(".jar")) diff = 1;
                else diff = 0;
                for (int i = 0; i < words.length-diff; i++) {
                    str += words[i]+"\\";
                }
                PrintWriter pwriter = new PrintWriter(new FileWriter(str+"companyCatalogue.txt", true));
                String name= nameOfMembership.getText().replace(" ", "_");
                pwriter.println("\n" + name + " " + Double.parseDouble(priceInput.getText()) + " " + Integer.parseInt(timeframe.getText()) + " N");
                pwriter.close();
                Stage stage = (Stage) enterButton.getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("SE_search.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Search");
                primaryStage.setScene(new Scene(root, 346.0, 621.0));
                primaryStage.show();

            }
        } catch (NullPointerException e) {
            errorLabel.setText("Please enter all the details");
        } catch (NumberFormatException e) {
            errorLabel.setText("Price and timeframe must be a number");
        }
    }

    public void libraryButtonClicked() throws IOException {
        Stage stage = (Stage) libraryButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("SE_mylib.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("My Library");
        primaryStage.setScene(new Scene(root, 346.0, 621.0));
        primaryStage.show();
    }

    public void historyButtonClicked() throws IOException {
        Stage stage = (Stage) historyButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("UserHistory.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("User History");
        primaryStage.setScene(new Scene(root, 346.0, 621.0));
        primaryStage.show();
    }

    public void backButtonClicked() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("SE_search.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Search catalogue");
        primaryStage.setScene(new Scene(root, 346.0, 621.0));
        primaryStage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date now = new Date();
        time.setText(sdf.format(now));
    }
}
