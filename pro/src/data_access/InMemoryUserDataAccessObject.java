package data_access;

import entity.User;
import service.read.use_case.ReadRecipeDataAccessInterface;
import service.save.use_case.SaveRecipeDataAccessInterface;
import service.signup.use_case.SignupUserDataAccessInterface;
import service.delete.use_case.DeleteRecipeDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SaveRecipeDataAccessInterface, DeleteRecipeDataAccessInterface,
        ReadRecipeDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void saveRecipe(String userName, String recipeName) {
        users.get(userName).setFavoriteRecipes(recipeName);
    }
    public ArrayList<String> loadFavouriteRecipes(String userName) {
        return users.get(userName).getFavoriteRecipes();
    }

    @Override
    public void deleteRecipe(String userName, String recipeName) {
        users.get(userName).getFavoriteRecipes().remove(recipeName);
    }
}
