package service.recommendation.use_case;

import entity.Recipe;

import java.util.Map;

public interface RecommendationDataAccessInterface {
    Map<String, Recipe> randomResult();
}
