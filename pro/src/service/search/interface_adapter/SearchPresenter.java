package service.search.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.after_search.interface_adapter.AfterSearchState;
import service.after_search.interface_adapter.AfterSearchViewModel;
import service.search.use_case.SearchOutputBoundary;
import service.search.use_case.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final AfterSearchViewModel afterSearchViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchPresenter(ViewManagerModel viewManagerModel,
                           AfterSearchViewModel afterSearchViewModel,
                           SearchViewModel searchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.afterSearchViewModel = afterSearchViewModel;
        this.searchViewModel = searchViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData result) {
        AfterSearchState afterSearchState = afterSearchViewModel.getState();
        afterSearchState.setSearchResult(result.getSearchResult());
        this.afterSearchViewModel.setState(afterSearchState);
        afterSearchViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(afterSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SearchState searchState = searchViewModel.getState();
        searchState.setNoResultError(error);
        searchViewModel.firePropertyChanged();
    }

}
