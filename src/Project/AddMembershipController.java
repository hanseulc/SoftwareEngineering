package Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;


public class AddMembershipController implements Initializable {

    @FXML
    private TextField nameOfMembership;
    @FXML
    private ChoiceBox choice;
    @FXML
    private RadioButton recurringRadio;
    @FXML
    private RadioButton fixedRadio;
    @FXML
    private TextField fixedLength;
    @FXML
    private Button enterButton;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField priceInput;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameOfMembership.setText(CatalogueController.chosenName);
        priceInput.setText(String.valueOf(CatalogueController.chosenPrice));
        choice.getItems().addAll("Annual", "Monthly", "Weekly");
        choice.setValue("Annual");
        endDate.setDisable(true);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date now = new Date();
        time.setText(sdf.format(now));
    }

    public void recurringSelected() {
        choice.setDisable(false);
        endDate.setDisable(true);
    }

    public void fixedSelected() {
        choice.setDisable(true);
        endDate.setDisable(false);

    }

    public void enterButtonClicked() throws IOException {
        try {
            RadioButton selectedButton = (RadioButton) fixedRadio.getToggleGroup().getSelectedToggle();
            String selected = selectedButton.getText();
            if (selected.equals("Fixed")) {
                if (startDate.getValue().isBefore(endDate.getValue())) {
                    Membership m = new Fixed(nameOfMembership.getText(), startDate.getValue(), Double.parseDouble(priceInput.getText()), endDate.getValue());
                    User.membershipList.add(m);
                    User.membershipAll.add(m);
                    LocalDate now = LocalDate.now();
                    boolean front = false;
                    if (m.getPaymentOrEndDate().isBefore(now)){
                        Parent root = FXMLLoader.load(getClass().getResource("FixedNotification.fxml"));
                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Notification");
                        primaryStage.setScene(new Scene(root, 200.0, 150.0));
                        primaryStage.show();
                        primaryStage.toFront();
                        front = true;
                    }
                    Stage stage = (Stage) enterButton.getScene().getWindow();
                    stage.close();
                    Parent root = FXMLLoader.load(getClass().getResource("SE_mylib.fxml"));
                    Stage primaryStage2 = new Stage();
                    primaryStage2.setTitle("My Library");
                    primaryStage2.setScene(new Scene(root, 346.0, 621.0));
                    primaryStage2.show();
                    if (front) primaryStage2.toBack();

                } else {
                    errorLabel.setText("Start date must be before end date");
                }
            } else {
                String type = choice.getSelectionModel().getSelectedItem().toString();
                if (type.equals("Annual")) {
                    Membership m = new Annual(nameOfMembership.getText(), startDate.getValue(), Double.parseDouble(priceInput.getText()));
                    User.membershipList.add(m);
                    User.membershipAll.add(m);
                }
                else if (type.equals("Monthly")) {
                    Membership m = new Monthly(nameOfMembership.getText(), startDate.getValue(), Double.parseDouble(priceInput.getText()));
                    User.membershipList.add(m);
                    User.membershipAll.add(m);
                }
                else {
                    Membership m = new Weekly(nameOfMembership.getText(), startDate.getValue(), Double.parseDouble(priceInput.getText()));
                    User.membershipList.add(m);
                    User.membershipAll.add(m);
                }
                Stage stage = (Stage) enterButton.getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("SE_mylib.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("My Library");
                primaryStage.setScene(new Scene(root, 346.0, 621.0));
                primaryStage.show();

            }

        } catch (NullPointerException e) {
            errorLabel.setText("Please enter the price and dates");
        } catch (NumberFormatException e) {
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
        Parent root = FXMLLoader.load(getClass().getResource("SE_search.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Search catalogue");
        primaryStage.setScene(new Scene(root, 346.0, 621.0));
        primaryStage.show();
    }


}
