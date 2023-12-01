package service.return_to_main.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.logout.interface_adapter.LogoutViewModel;
import service.return_to_main.use_case.ReturnToMainOutputBoundary;

public class ReturnToMainPresenter implements ReturnToMainOutputBoundary {
    private final ReturnToMainViewModel returnToMainViewModel;

    private final LogoutViewModel logoutViewModel;

    private final ViewManagerModel viewManagerModel;

    public ReturnToMainPresenter(ReturnToMainViewModel returnToMainViewModel, LogoutViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.returnToMainViewModel = returnToMainViewModel;
        this.logoutViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(logoutViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
