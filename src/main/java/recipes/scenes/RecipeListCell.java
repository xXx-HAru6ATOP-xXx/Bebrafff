package recipes.utility;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import recipes.scenes.CardController;

import java.io.IOException;

public class RecipeListCell extends ListCell<Recipe> {
    @Override
    protected void updateItem(Recipe recipe, boolean empty) {
        super.updateItem(recipe, empty);

        if (empty || recipe == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("card.fxml"));
            CardController controller = loader.getController();
            controller.setRecipe(recipe);
        }
    }
}
