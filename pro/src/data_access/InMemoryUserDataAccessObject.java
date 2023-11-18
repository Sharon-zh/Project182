package data_access;

import entity.User;
import service.save.use_case.SaveRecipeDataAccessInterface;
import service.signup.use_case.SignupUserDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SaveRecipeDataAccessInterface, SignupUserDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */
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
    public void saveRecipe(String userName, String recipeName) {
        users.get(userName).setFavoriteRecipes(recipeName);
    }
    public ArrayList<String> loadFavouriteRecipes(String userName) {
        return users.get(userName).getFavoriteRecipes();
    }
}
