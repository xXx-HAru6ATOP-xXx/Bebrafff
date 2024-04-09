package recipes.scenes;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreen extends Application {

    @FXML
    private Label mainHeader;
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("main-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);
        String absolutePath = "C:\\Users\\alina\\IdeaProjects\\Bebrafff\\src\\main\\resources\\img\\WERH.png";
        stage.getIcons().add(new Image("file:" + absolutePath));
        stage.setTitle("Toad's menu");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}