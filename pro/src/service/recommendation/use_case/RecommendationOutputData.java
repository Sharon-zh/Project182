package service.recommendation.use_case;

import entity.Recipe;
import entity.RecommendedRecipes;

import java.util.Map;

public class RecommendationOutputData {
    private final RecommendedRecipes random;

    public RecommendationOutputData(RecommendedRecipes random) {
        this.random = random;
    }

    public RecommendedRecipes randomResult() {
        return random;
    }
}
