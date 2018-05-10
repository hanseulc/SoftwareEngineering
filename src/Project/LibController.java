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
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class LibController implements Initializable {


    @FXML
    private Button addButton;
    @FXML
    private TableView<Membership> membershipTable;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn priceColumn;
    @FXML
    private TableColumn typeColumn;
    @FXML
    private TableColumn dateColumn;
    @FXML
    private TableView<LoyaltyScheme> loyaltyTable;
    @FXML
    private TableColumn loyaltyNameColumn;
    @FXML
    private TableColumn loyaltyPointsColumn;
    @FXML
    private Label errorLabel;
    @FXML
    private Label errorLabel2;
    @FXML
    private Label errorLabel3;
    @FXML
    private Button addPointsButton;
    @FXML
    private Button detailsButton;
    @FXML
    private Button removeLoyaltyButton;
    @FXML
    private Text monthString;
    @FXML
    private Text spendingString;
    @FXML
    private Button historyButton;
    @FXML
    private Button libraryButton;
    @FXML
    private Text time;

    protected static Membership chosenMembership;
    protected static LoyaltyScheme chosenLoyaltyScheme;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (User.loyaltyList.isEmpty()) {
            addPointsButton.setDisable(true);
            removeLoyaltyButton.setDisable(true);
        }
        if (User.membershipList.isEmpty()) {
            detailsButton.setDisable(true);
        }

        setMembershipTable();

        setLoyaltyTable();

        LocalDate local = LocalDate.now();
        monthString.setText("Monthly spending for " + local.getMonth().toString().toLowerCase() + ":");
        spendingString.setText("£" + calculateSpending());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date now = new Date();
        time.setText(sdf.format(now));

        try {
            User.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void setMembershipTable(){
        ArrayList<Membership> mems = User.membershipList;
        ObservableList<Membership> data = FXCollections.observableArrayList();
        LocalDate now = LocalDate.now();
        for (int i = 0; i < mems.size(); i++) {
            if (now.isAfter(mems.get(i).getPaymentOrEndDate()) && mems.get(i) instanceof Fixed){
                mems.get(i).setExpired();
                mems.remove(mems.get(i));
            }
            else {
                data.add(mems.get(i));
            }
        }


        membershipTable.setPlaceholder(new Label("No active memberships"));


        nameColumn.setCellValueFactory(new PropertyValueFactory<Membership, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Membership, Double>("price"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Membership, String>("type"));

        dateColumn.setCellValueFactory(new PropertyValueFactory<Membership, String>("paymentOrEndDateFormatted"));
        membershipTable.setItems(data);

    }

    public void setLoyaltyTable(){
        ArrayList<LoyaltyScheme> schemes = User.loyaltyList;
        ObservableList<LoyaltyScheme> data2 = FXCollections.observableArrayList();
        for (LoyaltyScheme l : schemes) {
            data2.add(l);
        }
        loyaltyTable.setPlaceholder(new Label("No active loyalty schemes"));

        loyaltyNameColumn.setCellValueFactory(new PropertyValueFactory<LoyaltyScheme, String>("name"));
        loyaltyPointsColumn.setCellValueFactory(new PropertyValueFactory<LoyaltyScheme, Integer>("points"));
        loyaltyTable.setItems(data2);
    }

    public void addPointsButtonClicked() throws IOException {
        try {
            LoyaltyScheme ls = loyaltyTable.getSelectionModel().getSelectedItem();
            ls.getName();
            chosenLoyaltyScheme = ls;

            Stage stage = (Stage) addPointsButton.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("LoyaltyView.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Loyalty Points View");
            primaryStage.setScene(new Scene(root, 346.0, 621.0));
            primaryStage.show();
        } catch (NullPointerException e) {
            errorLabel.setText("Please select a company from the table");
        }
    }

    public void addButton() throws IOException {
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("Options.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("options");
        primaryStage.setScene(new Scene(root, 346.0, 621.0));
        primaryStage.show();
    }

    public void detailsButtonClicked() throws IOException {
        try {
            Membership m = membershipTable.getSelectionModel().getSelectedItem();
            chosenMembership = m;
            m.getName();
            Stage stage = (Stage) addPointsButton.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("DetailsView.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Membership Details");
            primaryStage.setScene(new Scene(root, 346.0, 621.0));
            primaryStage.show();
        } catch (NullPointerException e) {
            errorLabel2.setText("Please select a company from the table");
        }
    }

    public void removeLoyaltyButtonClicked() throws IOException, URISyntaxException {
        try {
            LoyaltyScheme ls = loyaltyTable.getSelectionModel().getSelectedItem();
            ls.getName();
            User.loyaltyList.remove(ls);
            ls.setExpired();
            setLoyaltyTable();
            User.saveData();
        } catch (NullPointerException e) {
            errorLabel3.setText("Please select a company");
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

    public String calculateSpending() {
        ArrayList<Membership> mems = User.membershipList;
        LocalDate localDate = LocalDate.now();
        int monthVal = localDate.getMonthValue();
        int yearVal = localDate.getYear();
        double total = 0;
        for (Membership m : mems) {
            if (m.getStartDate().getMonthValue() == monthVal && m.getStartDate().getYear() == yearVal)
                total += m.getPrice();

            LocalDate paymentDate = m.getPaymentOrEndDate();
            if (!(m instanceof Fixed))
                if (m instanceof Weekly) {
                    LocalDate current = paymentDate;
                    int mult = 1;
                    while (current.plus(mult, ChronoUnit.WEEKS).getMonthValue() == monthVal) {
                        mult++;
                    }
                    mult--;
                    total += m.getPrice() * mult;
                } else {
                    if (paymentDate.getMonthValue() == monthVal && paymentDate.getYear() == yearVal)
                        total += m.getPrice();
                }
        }
        DecimalFormat df = new DecimalFormat("##.##");
        return df.format(total);
    }

}



