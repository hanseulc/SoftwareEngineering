package Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SE_DESIGN.fxml"));
        primaryStage.setTitle("Main page");
        primaryStage.setScene(new Scene(root, 346.0, 621.0));
        primaryStage.show();
    }


    public static void main(String[] args) throws URISyntaxException, IOException {
        new User();
        String path = String.valueOf((new File(CatalogueController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())));
        String[] words = path.split("\\\\");
        String str = "";
        int diff;
        if (path.contains(".jar")) diff = 1;
        else diff = 0;
        for (int i = 0; i < words.length-diff; i++) {
            str += words[i]+"\\";
        }
        File file = new File(str+"savedMemberships.txt");
        if(file.exists() && !file.isDirectory()) {
            User.readData();
        }
        launch(args);
    }
}
