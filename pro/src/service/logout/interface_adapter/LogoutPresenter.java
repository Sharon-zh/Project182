package service.logout.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.login.interface_adapter.LoginState;
import service.login.interface_adapter.LoginViewModel;
import service.logout.use_case.LogoutOutputBoundary;
import service.logout.use_case.LogoutOutputData;

public class LogoutPresenter implements LogoutOutputBoundary {
    private final LoginViewModel loginViewModel;

    private ViewManagerModel viewManagerModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData logoutOutputData) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(logoutOutputData.getUsername());
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
