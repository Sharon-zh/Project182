package app;

import interface_adapter.ViewManagerModel;
import service.login.interface_adapter.LoginViewModel;
import service.logout.interface_adapter.LogoutController;
import service.logout.interface_adapter.LogoutPresenter;
import service.logout.use_case.LogoutInputBoundary;
import service.logout.use_case.LogoutInteractor;
import service.logout.use_case.LogoutOutputBoundary;

import java.io.IOException;

public class LogoutUseCaseFactory {
    private LogoutUseCaseFactory() {}

    private static LogoutController createLogoutUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel, loginViewModel);
        LogoutInputBoundary logoutInteractor = new LogoutInteractor(
                logoutOutputBoundary);

        return new LogoutController(logoutInteractor);
    }
}
