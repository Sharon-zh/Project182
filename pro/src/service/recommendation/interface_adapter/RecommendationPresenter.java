package service.recommendation.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.recommendation.use_case.RecommendationOutputBoundary;
import service.recommendation.use_case.RecommendationOutputData;

public class RecommendationPresenter implements RecommendationOutputBoundary {
    private final RecommendationViewModel recommendationViewModel;
    private final ViewManagerModel viewManagerModel;


    public RecommendationPresenter(RecommendationViewModel recommendationViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.recommendationViewModel = recommendationViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(RecommendationOutputData response) {
        RecommendationState recommendationState = recommendationViewModel.getState();
        recommendationState.set(response.randomResult());
        this.recommendationViewModel.setState(recommendationState);
        this.recommendationViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(recommendationViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
