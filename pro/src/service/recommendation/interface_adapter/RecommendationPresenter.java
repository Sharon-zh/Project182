package service.recommendation.interface_adapter;

import service.recommendation.use_case.RecommendationOutputBoundary;
import service.recommendation.use_case.RecommendationOutputData;

public class RecommendationPresenter implements RecommendationOutputBoundary {
    private final RecommendationViewModel recommendationViewModel;

    public RecommendationPresenter(RecommendationViewModel recommendationViewModel) {
        this.recommendationViewModel = recommendationViewModel;
    }

    @Override
    public void prepareSuccessView(RecommendationOutputData response) {
        RecommendationState recommendationState = recommendationViewModel.getState();
        recommendationState.set(response.randomResult());
        this.recommendationViewModel.setState(recommendationState);
        this.recommendationViewModel.firePropertyChanged();
    }
}
