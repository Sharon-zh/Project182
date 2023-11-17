package entity;

import java.awt.*;
import java.util.ArrayList;
import entity.Recipe;

public class RecommendedRecipesService implements RecommendedRecipes{
    private final ArrayList<Recipe> recommendedRecipes;

    /**
     * @param recommendedRecipes
     */
    RecommendedRecipesService(ArrayList<Recipe> recommendedRecipes) {
        this.recommendedRecipes = recommendedRecipes;
    }

    @Override
    public ArrayList<Recipe> getRecommendedRecipes() {
        return recommendedRecipes;
    }
}
