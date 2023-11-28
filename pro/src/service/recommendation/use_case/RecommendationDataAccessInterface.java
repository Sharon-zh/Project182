package service.recommendation.use_case;

import entity.RecommendedRecipes;

public interface RecommendationDataAccessInterface {
    RecommendedRecipes randomResult();
}
