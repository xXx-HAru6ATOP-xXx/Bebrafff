package recipes.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import recipes.utility.DatabaseHandler;
import recipes.utility.Recipe;
import recipes.utility.Step;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class RecipeSceneController {
    private Stage stage;

    @FXML
    private Label recipeName;

    @FXML
    private Label description;

    @FXML
    private Label Calories;
    @FXML
    public ImageView resultPhoto;

    @FXML
    private Label Time;
    @FXML
    private void closeWindow() {
        // Закрытие дополнительного окна
        stage.close();
    }

    @FXML
    private ListView<Recipe> stepsListView;

    @FXML
    private Label ingredientString;
    public void setData(Recipe recipe) {
        recipeName.setText(recipe.getRecipeName());
        description.setText(recipe.getDescription());
        ingredientString.setText(listToString(recipe.getIngredients()));
        try {
            // Создаем объект URL из строки
            URL url = new URL("https:"+recipe.getResultPhoto());

            // Открываем поток для чтения данных изображения
            try (InputStream inputStream = url.openStream()) {
                // Создаем объект Image из потока данных
                Image image = new Image(inputStream);

                // Устанавливаем изображение в ImageView
                resultPhoto.setImage(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Обработка ошибок загрузки изображения
        }
    }
    private static String listToString(List<String> list) {
        StringJoiner joiner = new StringJoiner(", ");
        for (String element : list) {
            joiner.add(element);
        }
        return joiner.toString();
    }

}
