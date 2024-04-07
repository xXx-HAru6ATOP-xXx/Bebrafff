package recipes.utility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserRecipe {

    private String url;
    private DatabaseHandler dbHandler;

    //Конструктор класса, содержащий ссылку на страницу и ДБХэндлер
    public ParserRecipe(String url, DatabaseHandler dbHandler) {
        this.url = url;
        this.dbHandler = dbHandler;
    }
    public static Recipe parse(String url) {
        try {
            //Подключение к странице
            Document document = Jsoup.connect(url).get();

            //Получение String RecipeName
            String RecipeName = document.selectFirst("[itemprop=name]").text();

            //Получение Integer Calories
            String caloriesElement = document.selectFirst("[itemprop=calories]").text().replaceAll("[^0-9]", "");
            Integer Calories = Integer.parseInt(caloriesElement);

            //Получение Integer Time в минутах
            Element totalTimeMeta = document.selectFirst("meta[itemprop=totalTime]");
            String contentValue = totalTimeMeta.attr("content");
            Integer Time = PTToInteger.PTConvert(contentValue);

            //Получение String Description
            String Description = "";
            Element DescriptionBlock = document.selectFirst("[itemprop=description]");
            if(DescriptionBlock!=null) {
                Description = DescriptionBlock.text();}

            //Получение String ResultPhoto, являющейся ссылкой на фото
            Element ResultImageBlock = document.selectFirst("[class=resultphoto]");
            String ResultUrl = ResultImageBlock.attr("src");

            //Получение списка ингредиентов и сохранение их в List Ingredients
            List<String> Ingredients = new ArrayList<>();
            Elements recipeIngredients = document.select("[itemprop=recipeIngredient]");
            for (Element ingredient : recipeIngredients) {
                Ingredients.add(ingredient.text());
            }

            //Получение List шагов и List ссылок
            Element recipeInstructions = document.selectFirst("[itemprop=recipeInstructions]");
            List<String> Steps = new ArrayList<>();
            List<String> ImagesUrls = new ArrayList<>();

            if (recipeInstructions != null) {

                Integer stepNumber = 0;
                Elements pElements = recipeInstructions.select("p");
                for (Element pElement : pElements) {
                    if (!pElement.text().isEmpty()) {
                        stepNumber +=1;
                        Steps.add(pElement.text()); // добавил все шаги в Лист
                    }
                }

                Integer imageNumber = 0;
                Elements imgElements = recipeInstructions.select("img");
                for (Element imgElement : imgElements) {
                    imageNumber += 1;
                    String srcAttribute = imgElement.attr("src");
                    ImagesUrls.add("https:"+srcAttribute);
                }
            }

            Recipe recipe = new Recipe();
            recipe.setRecipeName(RecipeName);
            recipe.setCalories(Calories);
            recipe.setTime(Time);
            recipe.setDescription(Description);
            recipe.setResultPhoto(ResultUrl);
            recipe.setIngredients(Ingredients);
            recipe.setImagesUrls(ImagesUrls);
            recipe.setSteps(Steps);
            return recipe;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}