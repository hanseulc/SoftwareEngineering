package Project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class UserHistoryController implements Initializable {

    @FXML
    private TableView membershipHistoryTable;
    @FXML
    private TableColumn membershipNameColumn;
    @FXML
    private TableColumn membershipPriceColumn;
    @FXML
    private TableColumn membershipTypeColumn;
    @FXML
    private TableColumn membershipDateColumn;
    @FXML
    private TableColumn membershipStatusColumn;
    @FXML
    private TableView loyaltyHistoryTable;
    @FXML
    private TableColumn loyaltyNameColumn;
    @FXML
    private TableColumn loyaltyPointsColumn;
    @FXML
    private TableColumn loyaltyStatusColumn;
    @FXML
    private Button libraryButton;
    @FXML
    private Button historyButton;
    @FXML
    private Text time;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMembershipTable();
        setLoyaltyTable();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date now = new Date();
        time.setText(sdf.format(now));
    }

    public void setMembershipTable() {
        ArrayList<Membership> mems = User.membershipAll;
        ObservableList<Membership> data = FXCollections.observableArrayList();
        for (Membership m : mems) {
            data.add(m);
        }

        membershipHistoryTable.setPlaceholder(new Label("No active memberships"));


        membershipNameColumn.setCellValueFactory(new PropertyValueFactory<Membership, String>("name"));
        membershipPriceColumn.setCellValueFactory(new PropertyValueFactory<Membership, Double>("price"));
        membershipTypeColumn.setCellValueFactory(new PropertyValueFactory<Membership, String>("type"));
        membershipDateColumn.setCellValueFactory(new PropertyValueFactory<Membership, String>("paymentOrEndDateFormatted"));
        membershipStatusColumn.setCellValueFactory(new PropertyValueFactory<Membership, String>("status"));
        membershipHistoryTable.setItems(data);

    }

    public void setLoyaltyTable() {
        ArrayList<LoyaltyScheme> schemes = User.loyaltyAll;
        ObservableList<LoyaltyScheme> data2 = FXCollections.observableArrayList();
        for (LoyaltyScheme l : schemes) {
            data2.add(l);
        }
        loyaltyHistoryTable.setPlaceholder(new Label("No active loyalty schemes"));
        loyaltyNameColumn.setCellValueFactory(new PropertyValueFactory<LoyaltyScheme, String>("name"));
        loyaltyPointsColumn.setCellValueFactory(new PropertyValueFactory<LoyaltyScheme, Integer>("points"));
        loyaltyStatusColumn.setCellValueFactory(new PropertyValueFactory<LoyaltyScheme, String>("status"));
        loyaltyHistoryTable.setItems(data2);
    }

    public void libraryButtonClicked() throws IOException {
        Stage stage = (Stage) libraryButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("SE_mylib.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Library");
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

}
