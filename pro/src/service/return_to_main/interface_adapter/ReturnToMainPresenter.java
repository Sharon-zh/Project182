package service.return_to_main.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.logged_in.interface_adapter.LoggedInViewModel;
import service.return_to_main.use_case.ReturnToMainOutputBoundary;

public class ReturnToMainPresenter implements ReturnToMainOutputBoundary {
    private final ReturnToMainViewModel returnToMainViewModel;

    private final LoggedInViewModel loggedInViewModel;

    private final ViewManagerModel viewManagerModel;

    public ReturnToMainPresenter(ReturnToMainViewModel returnToMainViewModel, LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.returnToMainViewModel = returnToMainViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
