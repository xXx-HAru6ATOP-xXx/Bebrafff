package recipes.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AddWindowController {
    private Stage stage; // Обязательно должен быть проинициализирован перед открытием окна

    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setResizable(false);
    }
    @FXML
    private void closeWindow() {
        // Закрытие дополнительного окна
        stage.close();
    }
}
