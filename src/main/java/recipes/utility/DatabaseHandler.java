package recipes.utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    static Connection dbConnection;

    public static Connection GetDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/recipes";

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, "root", "root");
        return dbConnection;


    }

    public void insertRecipe(Recipe recipe) {
        try (Connection connection = GetDbConnection()) {
            connection.setAutoCommit(false); // Устанавливаем автокоммит в ручной режим

            // Вставка данных в таблицу maintable
            String maintableQuery = "INSERT INTO maintable (recipeName, calories, time, description, resultPhoto) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement maintableStatement = connection.prepareStatement(maintableQuery, Statement.RETURN_GENERATED_KEYS)) {
                maintableStatement.setString(1, recipe.getRecipeName());
                maintableStatement.setInt(2, recipe.getCalories());
                maintableStatement.setInt(3, recipe.getTime());
                maintableStatement.setString(4, recipe.getDescription());
                maintableStatement.setString(5, recipe.getResultPhoto());

                int affectedRows = maintableStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating recipe failed, no rows affected.");
                }

                // Получение сгенерированного ID
                int recipeId;
                try (ResultSet generatedKeys = maintableStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        recipeId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating recipe failed, no ID obtained.");
                    }
                }

                // Вставка данных в таблицу textsteps
                insertListIntoTable(recipe.getSteps(), recipeId, "textsteps", connection);

                // Вставка данных в таблицу imagesteps
                insertListIntoTable(recipe.getImagesUrls(), recipeId, "imagesteps", connection);

                // Вставка данных в таблицу ingredients
                insertListIntoTable(recipe.getIngredients(), recipeId, "ingredients", connection);

                connection.commit(); // Фиксируем транзакцию
            } catch (SQLException e) {
                connection.rollback(); // Откатываем транзакцию в случае ошибки
                e.printStackTrace();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void insertListIntoTable(List<String> list, int recipeId, String tableName, Connection connection) throws SQLException {
        String query = "INSERT INTO " + tableName + " (recipeId, number, item) VALUES (?, ?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            for (int i = 0; i < list.size(); i++) {
                statement.setInt(1, recipeId);
                statement.setInt(2, i);
                statement.setString(3, list.get(i));
                statement.addBatch();
            }

            statement.executeBatch();
        }
    }

    // Получение ВСЕХ рецептов из БД
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        try (Connection connection = GetDbConnection()) {
            String query = "SELECT * FROM maintable";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // получение данных из maintable
                        Recipe recipe = new Recipe();
                        int recipeId = resultSet.getInt("recipeId");
                        String recipeName = resultSet.getString("recipeName");
                        int calories = resultSet.getInt("calories");
                        int time = resultSet.getInt("time");
                        String description = resultSet.getString("description");
                        String resultPhoto = resultSet.getString("resultPhoto");
                        // сохранение их в класс
                        recipe.setRecipeId(recipeId);
                        recipe.setRecipeName(recipeName);
                        recipe.setCalories(calories);
                        recipe.setTime(time);
                        recipe.setDescription(description);
                        recipe.setResultPhoto(resultPhoto);

                        // Получение данных textsteps
                        String textStepsQuery = "SELECT * FROM textsteps WHERE recipeId = ?";
                        try (PreparedStatement textStepsStatement = connection.prepareStatement(textStepsQuery)) {
                            textStepsStatement.setInt(1, recipeId);
                            try (ResultSet textStepsResultSet = textStepsStatement.executeQuery()) {
                                List<String> textSteps = new ArrayList<>();
                                while (textStepsResultSet.next()) {
                                    String textStep = textStepsResultSet.getString("item");
                                    textSteps.add(textStep);
                                }
                                recipe.setSteps(textSteps); // и установка в класс
                            }
                        }

                        // Получение данных imagesteps
                        String imageStepsQuery = "SELECT * FROM imagesteps WHERE recipeId = ?";
                        try (PreparedStatement imageStepsStatement = connection.prepareStatement(imageStepsQuery)) {
                            imageStepsStatement.setInt(1, recipeId);
                            try (ResultSet imageStepsResultSet = imageStepsStatement.executeQuery()) {
                                List<String> imageSteps = new ArrayList<>();
                                while (imageStepsResultSet.next()) {
                                    String imageStep = imageStepsResultSet.getString("item");
                                    imageSteps.add(imageStep);
                                }
                                recipe.setImagesUrls(imageSteps); // и установка в класс
                            }
                        }

                        // Получение данных из ingredients
                        String ingredientsQuery = "SELECT * FROM ingredients WHERE recipeId = ?";
                        try (PreparedStatement ingredientsStatement = connection.prepareStatement(ingredientsQuery)) {
                            ingredientsStatement.setInt(1, recipeId);
                            try (ResultSet ingredientsResultSet = ingredientsStatement.executeQuery()) {
                                List<String> ingredients = new ArrayList<>();
                                while (ingredientsResultSet.next()) {
                                    String ingredient = ingredientsResultSet.getString("item");
                                    ingredients.add(ingredient);
                                }
                                recipe.setIngredients(ingredients); // и установка в класс
                            }
                        }
                        // Добавление объекта Recipe в список
                        recipes.add(recipe);

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return recipes;
    }

    public Recipe getRecipeById(Integer Id){
        Recipe recipe = new Recipe();
        try (Connection connection = GetDbConnection()) {
            String query = "SELECT * FROM maintable WHERE recipeid = "+Id;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // получение данных из maintable
                        int recipeId = resultSet.getInt("recipeId");
                        String recipeName = resultSet.getString("recipeName");
                        int calories = resultSet.getInt("calories");
                        int time = resultSet.getInt("time");
                        String description = resultSet.getString("description");
                        String resultPhoto = resultSet.getString("resultPhoto");
                        // сохранение их в класс
                        recipe.setRecipeId(recipeId);
                        recipe.setRecipeName(recipeName);
                        recipe.setCalories(calories);
                        recipe.setTime(time);
                        recipe.setDescription(description);
                        recipe.setResultPhoto(resultPhoto);

                        // Получение данных textsteps
                        String textStepsQuery = "SELECT * FROM textsteps WHERE recipeId = ?";
                        try (PreparedStatement textStepsStatement = connection.prepareStatement(textStepsQuery)) {
                            textStepsStatement.setInt(1, recipeId);
                            try (ResultSet textStepsResultSet = textStepsStatement.executeQuery()) {
                                List<String> textSteps = new ArrayList<>();
                                while (textStepsResultSet.next()) {
                                    String textStep = textStepsResultSet.getString("item");
                                    textSteps.add(textStep);
                                }
                                recipe.setSteps(textSteps); // и установка в класс
                            }
                        }

                        // Получение данных imagesteps
                        String imageStepsQuery = "SELECT * FROM imagesteps WHERE recipeId = ?";
                        try (PreparedStatement imageStepsStatement = connection.prepareStatement(imageStepsQuery)) {
                            imageStepsStatement.setInt(1, recipeId);
                            try (ResultSet imageStepsResultSet = imageStepsStatement.executeQuery()) {
                                List<String> imageSteps = new ArrayList<>();
                                while (imageStepsResultSet.next()) {
                                    String imageStep = imageStepsResultSet.getString("item");
                                    imageSteps.add(imageStep);
                                }
                                recipe.setImagesUrls(imageSteps); // и установка в класс
                            }
                        }

                        // Получение данных из ingredients
                        String ingredientsQuery = "SELECT * FROM ingredients WHERE recipeId = ?";
                        try (PreparedStatement ingredientsStatement = connection.prepareStatement(ingredientsQuery)) {
                            ingredientsStatement.setInt(1, recipeId);
                            try (ResultSet ingredientsResultSet = ingredientsStatement.executeQuery()) {
                                List<String> ingredients = new ArrayList<>();
                                while (ingredientsResultSet.next()) {
                                    String ingredient = ingredientsResultSet.getString("item");
                                    ingredients.add(ingredient);
                                }
                                recipe.setIngredients(ingredients); // и установка в класс
                            }
                        }
                        // Добавление объекта Recipe в список
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return recipe;
    }

    public void clearDb(){
        try (Connection connection = GetDbConnection()) {
            // Создаем объект Statement
            try (Statement statement = connection.createStatement()) {
                // Выполняем SQL-запрос для удаления всех записей из таблицы maintable
                String sql = "DELETE FROM maintable";
                statement.executeUpdate(sql);

                System.out.println("Все записи из таблицы maintable удалены.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        List<Recipe> allRecipes = databaseHandler.getAllRecipes();

        System.out.println(allRecipes);
    }

}