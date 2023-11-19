package data_access;

import entity.User;
import service.delete.use_case.DeleteRecipeDataAccessInterface;
import service.read.use_case.ReadRecipeDataAccessInterface;
import service.save.use_case.SaveRecipeDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SaveRecipeDataAccessInterface, DeleteRecipeDataAccessInterface,
        ReadRecipeDataAccessInterface {
    private final Map<String, User> accounts = new HashMap<>();
    @Override
    public void saveRecipe(String userName, String recipeName) {
        accounts.get(userName).setFavoriteRecipes(recipeName);
    }
    public ArrayList<String> loadFavouriteRecipes(String userName) {
        return accounts.get(userName).getFavoriteRecipes();
    }

    @Override
    public void deleteRecipe(String userName, String recipeName) {
        accounts.get(userName).getFavoriteRecipes().remove(recipeName);
    }
}
