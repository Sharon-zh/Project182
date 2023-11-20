package service.load_favourite_recipes.use_case;

import java.util.ArrayList;

public interface LoadRecipesDataAccessInterface {
    ArrayList<String> loadFavouriteRecipes(String userName);

}
