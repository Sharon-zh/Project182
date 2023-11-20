package service.recommendation.interface_adapter;


import service.recommendation.use_case.RecommendationInputBoundary;

public class RecommendationController {
    final RecommendationInputBoundary recommendationUseCaseInteractor;

    public RecommendationController(RecommendationInputBoundary recommendationUseCaseInteractor) {
        this.recommendationUseCaseInteractor = recommendationUseCaseInteractor;
    }

    public void execute(){
        recommendationUseCaseInteractor.execute();
    }
}
