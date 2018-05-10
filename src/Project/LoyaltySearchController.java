package Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class LoyaltySearchController implements Initializable {

    private String chosenNameLoyalty;
    private ArrayList<Company> companylist;
    @FXML
    private Button enterButton;
    @FXML
    private ListView list;
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

    public void readCatalogue() throws IOException, URISyntaxException {
        String path = String.valueOf((new File(LoyaltySearchController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())));
        String[] words = path.split("\\\\");
        String str = "";
        int diff;
        if (path.contains(".jar")) diff = 1;
        else diff = 0;
        for (int i = 0; i < words.length-diff; i++) {
            str += words[i]+"\\";
        }
        BufferedReader breader = new BufferedReader(new FileReader(str+"companyCatalogue.txt"));
        companylist = new ArrayList<>();
        String line;
        while ((line = breader.readLine()) != null && line.length() != 0) {
            String[] lines = line.split(" ");
            if (lines[3].equals("Y"))
                companylist.add(new Company(lines[0], Double.parseDouble(lines[1]), Integer.parseInt(lines[2]), false));
        }

        breader.close();
    }

    public void enterButtonClicked() throws IOException {
        try {
            chosenNameLoyalty = list.getSelectionModel().getSelectedItem().toString();
            boolean contains = false;
            for (LoyaltyScheme ls: User.loyaltyList) {
                if (ls.getName().equals(chosenNameLoyalty)){
                    contains = true;
                    break;
                }
            }
            if (contains){
                errorLabel.setText("You already have a loyalty scheme with this company");
            }
            else {
                LoyaltyScheme ls = new LoyaltyScheme(chosenNameLoyalty);
                User.loyaltyList.add(ls);
                User.loyaltyAll.add(ls);
                Stage stage = (Stage) enterButton.getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("SE_mylib.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Library");
                primaryStage.setScene(new Scene(root, 346.0, 621.0));
                primaryStage.show();
            }
        } catch (NullPointerException e) {
            errorLabel.setText("Please select a company");
        }
    }


    public void initialize(URL location, ResourceBundle resources) {
        try {
            readCatalogue();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        for (Company comp : companylist) {
            list.getItems().addAll(comp.getName());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date now = new Date();
        time.setText(sdf.format(now));

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
        Parent root = FXMLLoader.load(getClass().getResource("Options.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Services");
        primaryStage.setScene(new Scene(root, 346.0, 621.0));
        primaryStage.show();
    }
}
