package service.recommendation.use_case;

import entity.Recipe;

import java.util.ArrayList;
import java.util.Map;

public class RecommendationInteractor implements RecommendationInputBoundary{
    final RecommendationDataAccessInterface userDataAccessObject;
    final RecommendationOutputBoundary recommendationPresenter;

    public RecommendationInteractor(RecommendationDataAccessInterface userDataAccessObject, RecommendationOutputBoundary recommendationPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.recommendationPresenter = recommendationPresenter;
    }

    @Override
    public void execute() {
        Map<String, Recipe> random = userDataAccessObject.randomResult();
        RecommendationOutputData recommendationOutputData = new RecommendationOutputData(random);
        recommendationPresenter.prepareSuccessView(recommendationOutputData);
    }
}
