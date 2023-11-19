package service.logout.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.logout.use_case.LogoutOutputBoundary;
import service.search.interface_adapter.SearchViewModel;

public class LogoutPresenter implements LogoutOutputBoundary {
    private final SearchViewModel searchViewModel;
    private final LogoutViewModel logoutViewModel;

    private ViewManagerModel viewManagerModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel,
                           LogoutViewModel logoutViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel = searchViewModel;
        this.logoutViewModel = logoutViewModel;
    }

    @Override
    public void prepareSuccessView(String logoutMessage) {
        LogoutState logoutState = logoutViewModel.getState();
        logoutState.setLogoutMessage(logoutMessage);
        logoutViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
