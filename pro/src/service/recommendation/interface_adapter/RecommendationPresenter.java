package service.recommendation.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.check_recipe.interface_adapter.CheckRecipeState;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.recommendation.use_case.RecommendationOutputBoundary;
import service.recommendation.use_case.RecommendationOutputData;

public class RecommendationPresenter implements RecommendationOutputBoundary {
    private final RecommendationViewModel recommendationViewModel;
    private final ViewManagerModel viewManagerModel;

    private final CheckRecipeViewModel checkRecipeViewModel;


    public RecommendationPresenter(RecommendationViewModel recommendationViewModel,
                                   ViewManagerModel viewManagerModel,
                                   CheckRecipeViewModel checkRecipeViewModel) {
        this.recommendationViewModel = recommendationViewModel;
        this.viewManagerModel = viewManagerModel;
        this.checkRecipeViewModel = checkRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(RecommendationOutputData response) {
        RecommendationState recommendationState = recommendationViewModel.getState();
        recommendationState.set(response.randomResult());
        this.recommendationViewModel.setState(recommendationState);
        this.recommendationViewModel.firePropertyChanged();

        CheckRecipeState checkRecipeState = checkRecipeViewModel.getState();
        checkRecipeState.setUsername(recommendationState.getUsername());
        this.checkRecipeViewModel.setState(checkRecipeState);
        this.checkRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(recommendationViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
