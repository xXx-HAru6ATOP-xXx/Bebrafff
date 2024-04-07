package recipes.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import recipes.utility.Recipe;

import java.io.IOException;
import java.net.MalformedURLException;

public class StepListCell extends ListCell<Recipe> {
    @Override
    protected void updateItem(Recipe recipe, boolean empty) {
        super.updateItem(recipe, empty);

        if (empty || recipe == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("step.fxml"));
            try {
                setGraphic(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            StepController controller = loader.getController();
            try {
                controller.setStep(recipe);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
