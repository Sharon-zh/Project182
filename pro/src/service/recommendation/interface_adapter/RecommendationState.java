package service.recommendation.interface_adapter;

import entity.Recipe;
import entity.RecommendedRecipes;
import entity.RecommendedRecipesService;

import java.util.HashMap;
import java.util.Map;

public class RecommendationState {
    private RecommendedRecipes random;

    public RecommendationState(){}

    public RecommendedRecipes get(){
        return random;
    }

    public void set(RecommendedRecipes random) {
        this.random = random;
    }
}
