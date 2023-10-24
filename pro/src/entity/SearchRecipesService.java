package entity;

import java.util.Map;
import entity.Recipe;


public class SearchRecipesService implements SearchRecipes{
    private final Map<String, Recipe[]> searchRecipes;

    /**
     * @param searchRecipes
     */
    SearchRecipesService(Map<String, Recipe[]> searchRecipes) {
        this.searchRecipes = searchRecipes;
    }

    @Override
    public Map<String, Recipe[]> getSearchRecipes() {
        return searchRecipes;
    }
}
