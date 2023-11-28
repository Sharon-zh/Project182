package data_access;

import entity.User;
import service.load_favourite_recipes.use_case.LoadRecipesDataAccessInterface;
import service.login.use_case.LoginUserDataAccessInterface;
import service.save_favourite_recipe.use_case.SaveRecipeDataAccessInterface;
import service.remove_favourite_recipe.use_case.RemoveRecipeDataAccessInterface;
import service.signup.use_case.SignupUserDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SaveRecipeDataAccessInterface, RemoveRecipeDataAccessInterface,
        LoadRecipesDataAccessInterface, SignupUserDataAccessInterface, LoginUserDataAccessInterface {
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

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * @param user the data to save
     */
    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }
}
