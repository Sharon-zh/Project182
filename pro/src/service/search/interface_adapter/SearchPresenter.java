package service.search.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.search.use_case.SearchOutputBoundary;
import service.search.use_case.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final CheckRecipeViewModel checkRecipeViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel,
                           CheckRecipeViewModel checkRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
        this.checkRecipeViewModel = checkRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData result) {
        SearchState searchState = searchViewModel.getState();
        searchState.setSearchResult(result.getSearchResult());
        searchViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(checkRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SearchState searchState = searchViewModel.getState();
        searchState.setNoResultError(error);
        searchViewModel.firePropertyChanged();
    }

}
