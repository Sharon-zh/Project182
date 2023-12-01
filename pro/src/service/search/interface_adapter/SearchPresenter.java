package service.search.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.check_recipe.interface_adapter.CheckRecipeState;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.recommendation.interface_adapter.RecommendationState;
import service.recommendation.interface_adapter.RecommendationViewModel;
import service.search.use_case.SearchOutputBoundary;
import service.search.use_case.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;

    private final CheckRecipeViewModel checkRecipeViewModel;

    public SearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, CheckRecipeViewModel checkRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
        this.checkRecipeViewModel = checkRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData result) {
        SearchState searchState = searchViewModel.getState();
        searchState.setSearchResult(result.getSearchResult());
        searchState.setNoResultError(null);
        this.searchViewModel.setState(searchState);
        this.searchViewModel.firePropertyChanged();

        CheckRecipeState checkRecipeState = checkRecipeViewModel.getState();
        checkRecipeState.setUsername(searchState.getUsername());
        this.checkRecipeViewModel.setState(checkRecipeState);
        this.checkRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(searchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SearchState searchState = searchViewModel.getState();
        searchState.setNoResultError(error);
        searchViewModel.firePropertyChanged();
    }

}
