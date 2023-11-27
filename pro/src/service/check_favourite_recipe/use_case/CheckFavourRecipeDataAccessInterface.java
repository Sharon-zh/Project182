package service.check_favourite_recipe.use_case;

import entity.Recipe;

import java.util.Map;

public interface CheckFavourRecipeDataAccessInterface {
    Map<String, Recipe> searchResult(String searchWord);
    boolean hasResult(Map<String, Recipe> result);
}
