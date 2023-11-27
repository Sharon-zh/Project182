package app;

import data_access.FileRecipeDataAccessObject;
import data_access.FileUserDataAccessObject;
import data_access.LikeFileRecipeDateAccessObject;
import entity.CommonRecipeFactory;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import service.comment.interface_adapter.CommentViewModel;
import service.load_favourite_recipes.interface_adapter.LoadRecipesViewModel;
import view.SignupView;
import view.ViewManager;
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

        // Add View to views
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);
        //LoginView
        //SearchView
        //SearchResultView
        //RecommendationView
        //RecipeView

        // Set the beginning View(should change to log in)
        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}

