package service.after_search.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.after_search.use_case.AfterSearchOutputBoundary;
import service.search.interface_adapter.SearchViewModel;

public class AfterSearchPresenter implements AfterSearchOutputBoundary {
    private final SearchViewModel searchViewModel;

    private ViewManagerModel viewManagerModel;

    public AfterSearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
    }

    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
