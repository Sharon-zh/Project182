package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import service.logged_in.interface_adapter.LoggedInViewModel;
import service.login.interface_adapter.LoginController;
import service.login.interface_adapter.LoginPresenter;
import service.login.interface_adapter.LoginViewModel;
import service.login.use_case.LoginInputBoundary;
import service.login.use_case.LoginInteractor;
import service.login.use_case.LoginOutputBoundary;
import service.login.use_case.LoginUserDataAccessInterface;
import service.logout.interface_adapter.LogoutViewModel;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            LogoutViewModel logoutViewModel) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, logoutViewModel);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            LogoutViewModel logoutViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel, logoutViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
