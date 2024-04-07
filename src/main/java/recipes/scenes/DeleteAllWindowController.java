package recipes.scenes;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import recipes.utility.DatabaseHandler;

public class DeleteAllWindowController {
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
    @FXML
    private void onYesClick(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.clearDb();
        System.exit(1);
    }
}
