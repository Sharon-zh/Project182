package service.login.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.login.use_case.LoginOutputBoundary;
import service.login.use_case.LoginOutputData;
import service.logout.interface_adapter.LogoutViewModel;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LogoutViewModel logoutViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel,LogoutViewModel logoutViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.logoutViewModel = logoutViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        this.loginViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(logoutViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
