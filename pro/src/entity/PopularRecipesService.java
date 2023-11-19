package entity;

import java.util.ArrayList;
import entity.Recipe;


public class PopularRecipesService implements PopularRecipes{
    private final ArrayList<Recipe> topTenRecipes;

    /**
     * @param topTenRecipes
     */
    public PopularRecipesService(ArrayList<Recipe> topTenRecipes) {
        this.topTenRecipes = topTenRecipes;
    }

    @Override
    public ArrayList<Recipe> getTopTenRecipes() {
        return topTenRecipes;
    }
}
