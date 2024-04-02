package recipes.nicefood;

import java.util.List;

public class Recipe {
    private Integer RecipeId;
    private String RecipeName;
    private Integer Calories;
    private Integer Time;
    private String Description;
    private String ResultPhoto;
    private List<String> Steps;
    private List<String> ImagesUrls;
    private Boolean FavoriteStatus;

    //Конструктор с ID
    public Recipe(Integer recipeId, String recipeName, Integer calories, Integer time, String description, String resultPhoto, List<String> steps, List<String> imagesUrls, Boolean favoriteStatus) {
        this.RecipeId = recipeId;
        this.RecipeName = recipeName;
        this.Calories = calories;
        this.Time = time;
        this.Description = description;
        this.ResultPhoto = resultPhoto;
        this.Steps = steps;
        this.ImagesUrls = imagesUrls;
        this.FavoriteStatus = favoriteStatus;
    }
    //Конструктор без ID
    public Recipe(String recipeName, Integer calories, Integer time, String description, String resultPhoto, List<String> steps, List<String> imagesUrls, Boolean favoriteStatus) {
        this.RecipeName = recipeName;
        this.Calories = calories;
        this.Time = time;
        this.Description = description;
        this.ResultPhoto = resultPhoto;
        this.Steps = steps;
        this.ImagesUrls = imagesUrls;
        this.FavoriteStatus = favoriteStatus;
    }

    //Инициализатор пустого класса
    public Recipe() {}

    //Сеттеры значений
    public void setRecipeId(Integer recipeId) {
        RecipeId = recipeId;
    }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }

    public void setCalories(Integer calories) {
        Calories = calories;
    }

    public void setTime(Integer time) {
        Time = time;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setResultPhoto(String resultPhoto) {
        ResultPhoto = resultPhoto;
    }

    public void setSteps(List<String> steps) {
        Steps = steps;
    }

    public void setImagesUrls(List<String> imagesUrls) {
        ImagesUrls = imagesUrls;
    }

    public void setFavoriteStatus(Boolean favoriteStatus) {
        FavoriteStatus = favoriteStatus;
    }

    //Геттеры значений
    public Integer getRecipeId() {
        return RecipeId;
    }

    public String getRecipeName() {
        return RecipeName;
    }

    public Integer getCalories() {
        return Calories;
    }

    public Integer getTime() {
        return Time;
    }

    public String getDescription() {
        return Description;
    }

    public String getResultPhoto() {
        return ResultPhoto;
    }

    public List<String> getSteps() {
        return Steps;
    }

    public List<String> getImagesUrls() {
        return ImagesUrls;
    }

    public Boolean getFavoriteStatus() {
        return FavoriteStatus;
    }
}
