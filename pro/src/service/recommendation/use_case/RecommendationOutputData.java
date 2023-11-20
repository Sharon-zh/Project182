package service.recommendation.use_case;

import entity.Recipe;

import java.util.Map;

public class RecommendationOutputData {
    private final Map<String, Recipe> random;

    public RecommendationOutputData(Map<String, Recipe> random) {
        this.random = random;
    }

    public Map<String, Recipe> randomResult() {
        return random;
    }
}
