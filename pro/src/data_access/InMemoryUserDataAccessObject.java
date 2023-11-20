package data_access;

import entity.User;
import service.load_favourite_recipes.use_case.LoadRecipesDataAccessInterface;
import service.save_favourite_recipe.use_case.SaveRecipeDataAccessInterface;
import service.remove_favourite_recipe.use_case.RemoveRecipeDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SaveRecipeDataAccessInterface, RemoveRecipeDataAccessInterface,
        LoadRecipesDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void saveRecipe(String userName, String recipeName) {
        users.get(userName).setFavoriteRecipes(recipeName);
    }
    public ArrayList<String> loadFavouriteRecipes(String userName) {
        return users.get(userName).getFavoriteRecipes();
    }

    @Override
    public void removeRecipe(String userName, String recipeName) {
        users.get(userName).getFavoriteRecipes().remove(recipeName);
    }
}
