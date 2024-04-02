package recipes.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import recipes.utility.DatabaseHandler;
import recipes.utility.Recipe;
import recipes.utility.RecipeListCell;

import java.util.List;

public class MainScreenController {
    @FXML
    private ListView<Recipe> recipeListView;

    public void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        List<Recipe> allRecipes = databaseHandler.getAllRecipes(); // Метод для получения списка рецептов
        recipeListView.getItems().addAll(allRecipes);
        recipeListView.setCellFactory(param -> new RecipeListCell());
    }

}