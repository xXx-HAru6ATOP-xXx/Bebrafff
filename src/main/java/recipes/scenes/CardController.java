package recipes.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import recipes.utility.DatabaseHandler;
import recipes.utility.Recipe;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class CardController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label idLabel;

    public ImageView resultPhoto;

    public void setRecipe(Recipe recipe) throws MalformedURLException {
        nameLabel.setText(recipe.getRecipeName());
        descriptionLabel.setText(recipe.getDescription());
        idLabel.setText(String.valueOf(recipe.getRecipeId()));
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




    @FXML
    private void openNewSceneWithData() {
        String labelText = idLabel.getText();
        int recipeId = Integer.parseInt(labelText);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("recipe-screen.fxml"));
            Parent root = loader.load();
            DatabaseHandler databaseHandler = new DatabaseHandler();
            Recipe recipe = databaseHandler.getRecipeById(recipeId);
            RecipeSceneController recipeSceneController = loader.getController();
            recipeSceneController.setData(recipe); // Передаем текущий рецепт в новый контроллер

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
