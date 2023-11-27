package app;

import data_access.FileRecipeDataAccessObject;
import data_access.FileUserDataAccessObject;
import data_access.LikeFileRecipeDateAccessObject;
import data_access.RandomRecipeDataAccessObject;
import entity.CommonRecipeFactory;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.comment.interface_adapter.CommentViewModel;
import service.load_favourite_recipes.interface_adapter.LoadRecipesViewModel;
import service.logged_in.interface_adapter.LoggedInViewModel;
import service.recommendation.interface_adapter.RecommendationViewModel;
import service.recommendation.use_case.RecommendationDataAccessInterface;
import service.return_to_main.interface_adapter.ReturnToMainViewModel;
import view.SignupView;
import view.ViewManager;
import view.RecommendView;
import service.signup.interface_adapter.SignupViewModel;
import service.login.interface_adapter.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        // The main application window.
        JFrame application = new JFrame("Recipe Project");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Create ViewModel(all use case)
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        CommentViewModel commentViewModel = new CommentViewModel();
        LoadRecipesViewModel loadRecipesViewModel = new LoadRecipesViewModel();
        RecommendationViewModel recommendationViewModel = new RecommendationViewModel();
        CheckRecipeViewModel checkRecipeViewModel = new CheckRecipeViewModel();
        ReturnToMainViewModel returnToMainViewModel = new ReturnToMainViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();


        // Create File DAO
        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./user.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileRecipeDataAccessObject recipeDataAccessObject;
        try {
            recipeDataAccessObject = new FileRecipeDataAccessObject("./comment.csv", new CommonRecipeFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LikeFileRecipeDateAccessObject likeFileRecipeDateAccessObject;
        try {
            likeFileRecipeDateAccessObject = new LikeFileRecipeDateAccessObject("./like_num.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        RandomRecipeDataAccessObject randomRecipeDataAccessObject = new RandomRecipeDataAccessObject(new CommonRecipeFactory(), recipeDataAccessObject, likeFileRecipeDateAccessObject);

        // Add View to views
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);
        //LoginView
        //SearchView
        //SearchResultView
        RecommendView recommendView = RecommendationUseCaseFactory.create(viewManagerModel, checkRecipeViewModel, recommendationViewModel, randomRecipeDataAccessObject, returnToMainViewModel, loggedInViewModel);
        views.add(recommendView, recommendView.viewName);
        //RecipeView

        // Set the beginning View(should change to log in)
        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}

