package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import service.jump_to_signup.interface_adapter.JumpToSignupController;
import service.jump_to_signup.interface_adapter.JumpToSignupViewModel;
import service.load_favourite_recipes.interface_adapter.LoadRecipesViewModel;
import service.login.interface_adapter.LoginController;
import service.login.interface_adapter.LoginPresenter;
import service.login.interface_adapter.LoginViewModel;
import service.login.use_case.LoginInputBoundary;
import service.login.use_case.LoginInteractor;
import service.login.use_case.LoginOutputBoundary;
import service.login.use_case.LoginUserDataAccessInterface;
import service.logout.interface_adapter.LogoutViewModel;
import service.recommendation.interface_adapter.RecommendationViewModel;
import service.search.interface_adapter.SearchViewModel;
import service.signup.interface_adapter.SignupViewModel;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            LogoutViewModel logoutViewModel, SearchViewModel searchViewModel, RecommendationViewModel recommendationViewModel, LoadRecipesViewModel loadRecipesViewModel,
            JumpToSignupViewModel jumpToSignupViewModel, SignupViewModel signupViewModel) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, userDataAccessObject, logoutViewModel, searchViewModel, recommendationViewModel, loadRecipesViewModel);
            JumpToSignupController jumpToSignupController = JumpToSignupUseCaseFactory.creatJumpToSignupUseCase(jumpToSignupViewModel, signupViewModel, viewManagerModel);
            return new LoginView(loginViewModel, loginController, jumpToSignupController, jumpToSignupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            LogoutViewModel logoutViewModel,
            SearchViewModel searchViewModel, RecommendationViewModel recommendationViewModel,
            LoadRecipesViewModel loadRecipesViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel, logoutViewModel, searchViewModel, recommendationViewModel, loadRecipesViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
