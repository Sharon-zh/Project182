package data_access;

import entity.User;
import service.remove_favourite_recipe.use_case.RemoveRecipeDataAccessInterface;
import service.load_favourite_recipes.use_case.LoadRecipesDataAccessInterface;
import service.save_favourite_recipe.use_case.SaveRecipeDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SaveRecipeDataAccessInterface, RemoveRecipeDataAccessInterface,
        LoadRecipesDataAccessInterface {
    private final Map<String, User> accounts = new HashMap<>();
    @Override
    public void saveRecipe(String userName, String recipeName) {
        accounts.get(userName).setFavoriteRecipes(recipeName);
    }
    public ArrayList<String> loadFavouriteRecipes(String userName) {
        return accounts.get(userName).getFavoriteRecipes();
    }

    @Override
    public void removeRecipe(String userName, String recipeName) {
        accounts.get(userName).getFavoriteRecipes().remove(recipeName);
    }
}
