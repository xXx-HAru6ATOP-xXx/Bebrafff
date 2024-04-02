package recipes.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import recipes.utility.Recipe;

public class CardController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label descriptionLabel;

    public void setRecipe(Recipe recipe) {
        nameLabel.setText(recipe.getRecipeName());
        descriptionLabel.setText(recipe.getDescription());
    }
}
