package recipes.scenes;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import recipes.utility.DatabaseHandler;
import recipes.utility.ParserRecipe;
import recipes.utility.Recipe;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @FXML
    private TextField inputTextField;

    @FXML
    private Label statusLabel;
    @FXML
    private void TextFieldProcessing() throws SQLException, ClassNotFoundException {
        String inputValue = inputTextField.getText();
        System.out.println(inputValue);
        Boolean check = isRecipeLink(inputValue);
        if (check) { // если ссылка то
            DatabaseHandler databaseHandler = new DatabaseHandler();
            Recipe recipe = ParserRecipe.parse(inputValue);
            databaseHandler.insertRecipe(recipe);
            closeWindow();
        } else { // если не ссылка, то
            statusLabel.setText("Неверная ссылка");
        }
    }

    public static boolean isRecipeLink(String input) {
        // Паттерн для проверки строки на соответствие формату ссылки на рецепт
        Pattern pattern = Pattern.compile("https://www\\.iamcook\\.ru/showrecipe/\\d+");
        Matcher matcher = pattern.matcher(input);


        return matcher.matches();
    }
}
