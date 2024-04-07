package recipes.scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TopBarController {
    @FXML
    private void openAddWindow() {
        try {
            System.out.println();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-window.fxml"));
            Parent root = loader.load();

            Stage addWindowStage = new Stage();
            addWindowStage.initModality(Modality.APPLICATION_MODAL);
            addWindowStage.setTitle("Добавление рецепта");
            addWindowStage.setScene(new Scene(root));

            AddWindowController addWindowController = loader.getController();
            addWindowController.setStage(addWindowStage);

            addWindowStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openDeleteAllWindow() {
        try {
            System.out.println();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteall.fxml"));
            Parent root = loader.load();

            Stage addWindowStage = new Stage();
            addWindowStage.initModality(Modality.APPLICATION_MODAL);
            addWindowStage.setTitle("Сброс всех рецептов");
            addWindowStage.setScene(new Scene(root));

            DeleteAllWindowController deleteAllWindowController = loader.getController();
            deleteAllWindowController.setStage(addWindowStage);

            addWindowStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
