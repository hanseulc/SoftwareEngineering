package Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class EditMembershipController implements Initializable{

    @FXML
    private TextField nameOfMembership;
    @FXML
    private TextField priceOfMembership;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Button enterButton;
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
    private Boolean labelSet = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Membership m = LibController.chosenMembership;
        nameOfMembership.setText(m.getName());
        priceOfMembership.setText(String.valueOf(m.getPrice()));
        startDate.setValue(m.getStartDate());
        if (m instanceof Recurring){
            endDate.setDisable(true);
        }
        else{
            endDate.setValue(m.getPaymentOrEndDate());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date now = new Date();
        time.setText(sdf.format(now));
    }

    public void enterButtonClicked() throws IOException {
        try{
            Membership m = LibController.chosenMembership;
            if (m instanceof Fixed) {
                if (startDate.getValue().isAfter(endDate.getValue())) {
                    errorLabel.setText("Start date must be before end date");
                    labelSet = true;
                }
            }
            if (!labelSet) {
                m.setName(nameOfMembership.getText());
                m.setPrice(Double.parseDouble(priceOfMembership.getText()));
                m.setStartDate(startDate.getValue());
                if (m instanceof Fixed) {
                    m.setPaymentOrEndDate(endDate.getValue());
                }
                Stage stage = (Stage) enterButton.getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("SE_mylib.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("My Library");
                primaryStage.setScene(new Scene(root, 346.0, 621.0));
                primaryStage.show();
            }
            labelSet = false;
        }
        catch (NullPointerException e){
            errorLabel.setText("Please enter all the information");

        }
        catch (NumberFormatException e){
            errorLabel.setText("Price must be a number");
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
        Parent root = FXMLLoader.load(getClass().getResource("DetailsView.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Membership details");
        primaryStage.setScene(new Scene(root, 346.0, 621.0));
        primaryStage.show();
    }

}
