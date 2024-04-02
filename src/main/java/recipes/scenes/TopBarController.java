package recipes.controllers;

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
            addWindowStage.setTitle("Дополнительное окно");
            addWindowStage.setScene(new Scene(root));

            AddWindowController addWindowController = loader.getController();
            addWindowController.setStage(addWindowStage);

            addWindowStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
