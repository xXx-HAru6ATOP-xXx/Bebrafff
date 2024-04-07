package recipes.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import recipes.utility.Recipe;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class StepController {

    @FXML
    private Label stepText;

    @FXML
    private ImageView stepImage;

    void setStep(Recipe recipe) throws MalformedURLException {
        try {
            // Создаем объект URL из строки
            URL url = new URL("https:"+recipe.getResultPhoto());

            // Открываем поток для чтения данных изображения
            try (InputStream inputStream = url.openStream()) {
                // Создаем объект Image из потока данных
                Image image = new Image(inputStream);

                // Устанавливаем изображение в ImageView
                stepImage.setImage(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Обработка ошибок загрузки изображения
        }
    }
}
