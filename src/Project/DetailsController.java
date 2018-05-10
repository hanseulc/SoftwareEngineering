package Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class DetailsController implements Initializable{

    @FXML
    private Text membershipName;
    @FXML
    private Text membershipPrice;
    @FXML
    private Text membershipType;
    @FXML
    private Text membershipStart;
    @FXML
    private Text membershipEnd;
    @FXML
    private Text paymentDate;
    @FXML
    private Button removeButton;
    @FXML
    private Button editButton;
    @FXML
    private Button libraryButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button backButton;
    @FXML
    private Text time;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            Membership m = LibController.chosenMembership;
            membershipName.setText(m.getName());
            membershipPrice.setText("£" + String.valueOf(m.getPrice()));
            membershipType.setText(m.getType());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            membershipStart.setText(m.getStartDate().format(formatter));
            if (m instanceof Fixed) {
                Fixed f = (Fixed) m;
                membershipEnd.setText(f.getExpiryDate().format(formatter));
            } else {
                membershipEnd.setText("No expiry date");
            }
            if (m instanceof Recurring) {
                Recurring r = (Recurring) m;
                LocalDate now = LocalDate.now();
                if (r.getPaymentDate().isBefore(now))
                    r.setPaymentDate();
                paymentDate.setText(r.getPaymentDate().format(formatter));
            } else {
                paymentDate.setText("No recurring payment date");
            }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date now = new Date();
        time.setText(sdf.format(now));

    }

    public void removeButtonClicked() throws IOException {
        Membership m = LibController.chosenMembership;
        User.membershipList.remove(m);
        m.setExpired();
        Stage stage = (Stage) removeButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("SE_mylib.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Library");
        primaryStage.setScene(new Scene(root, 346.0, 621.0));
        primaryStage.show();
    }

    public void editButtonClicked() throws IOException {
        Stage stage = (Stage) editButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("EditMembership.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Edit Membership");
        primaryStage.setScene(new Scene(root, 346.0, 621.0));
        primaryStage.show();
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
        Parent root = FXMLLoader.load(getClass().getResource("SE_mylib.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Library");
        primaryStage.setScene(new Scene(root, 346.0, 621.0));
        primaryStage.show();
    }
}
