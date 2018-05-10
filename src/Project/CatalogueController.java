package Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class CatalogueController implements Initializable {


    @FXML
    private ListView list;
    @FXML
    private Button enterButton;
    @FXML
    private Label errorLabel;
    @FXML
    private Button addCustomButton;
    @FXML
    private Button libraryButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button backButton;
    @FXML
    private Text time;

    private ArrayList<Company> companylist;
    protected static String chosenName;
    protected static double chosenPrice;

    public void readCatalogue() throws IOException, URISyntaxException {
        String path = String.valueOf((new File(CatalogueController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())));
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
        while ((line = breader.readLine()) != null) {
            try {
                String[] lines = line.split(" ");
                boolean hasLoyalty = lines[3].equals("Y") ? true : false;
                companylist.add(new Company(lines[0], Double.parseDouble(lines[1]), Integer.parseInt(lines[2]), hasLoyalty));
            }
            catch (ArrayIndexOutOfBoundsException e){}
        }
        breader.close();
    }


    public void enterClicked() throws IOException {
        try {
            chosenName = list.getSelectionModel().getSelectedItem().toString();
            chosenPrice = findPrice();
            Stage stage = (Stage) enterButton.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("SE_addmem.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Add Membership");
            primaryStage.setScene(new Scene(root, 346.0, 621.0));
            primaryStage.show();
        } catch (NullPointerException e) {
            errorLabel.setText("Please select a company");
        }
    }

    public void addCustomButtonClicked() throws IOException {
        Stage stage = (Stage) addCustomButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("AddCustomMembership.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Add Custom Membership");
        primaryStage.setScene(new Scene(root, 346.0, 621.0));
        primaryStage.show();
    }

    public double findPrice() {
        for (Company comp : companylist)
            if (chosenName.equals(comp.getName()))
                return comp.getPrice();
        return 0;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            readCatalogue();
        }
         catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Company comp : companylist)
            list.getItems().addAll(comp.getName());

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


