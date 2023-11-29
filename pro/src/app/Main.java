package app;

import data_access.*;
import entity.CommonRecipeFactory;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeViewModel;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.comment.interface_adapter.CommentViewModel;
import service.load_favourite_recipes.interface_adapter.LoadRecipesViewModel;
import service.logged_in.interface_adapter.LoggedInViewModel;
import service.logout.interface_adapter.LogoutViewModel;
import service.recommendation.interface_adapter.RecommendationViewModel;
import service.recommendation.use_case.RecommendationDataAccessInterface;
import service.remove_favourite_recipe.interface_adapter.RemoveRecipeViewModel;
import service.return_to_main.interface_adapter.ReturnToMainViewModel;
import service.search.interface_adapter.SearchViewModel;
import view.*;
import service.signup.interface_adapter.SignupViewModel;
import service.login.interface_adapter.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

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
        SearchViewModel searchViewModel = new SearchViewModel();
        LogoutViewModel logoutViewModel = new LogoutViewModel();
        CheckFavourRecipeViewModel checkFavourRecipeViewModel = new CheckFavourRecipeViewModel();
        RemoveRecipeViewModel removeRecipeViewModel = new RemoveRecipeViewModel();


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

        ApiRecipeDataAccessObject apiRecipeDataAccessObject = new ApiRecipeDataAccessObject(new CommonRecipeFactory(), recipeDataAccessObject, likeFileRecipeDateAccessObject);

        RandomRecipeDataAccessObject randomRecipeDataAccessObject = new RandomRecipeDataAccessObject(new CommonRecipeFactory(), recipeDataAccessObject, likeFileRecipeDateAccessObject);

        // Add View to views
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, logoutViewModel);

        views.add(loginView, loginView.viewName);

        SearchView searchView = LoadRecipesUseCaseFactory.create(viewManagerModel, searchViewModel, checkRecipeViewModel, apiRecipeDataAccessObject, logoutViewModel, recommendationViewModel, loadRecipesViewModel, loggedInViewModel, userDataAccessObject, randomRecipeDataAccessObject, loginViewModel);
        views.add(searchView, searchView.viewName);
        SearchResultView searchResultView = SearchUseCaseFactory.create(searchViewModel, checkRecipeViewModel, returnToMainViewModel, viewManagerModel, loggedInViewModel, apiRecipeDataAccessObject);
        views.add(searchResultView, searchResultView.viewName);
        RecommendView recommendView = RecommendationUseCaseFactory.create(viewManagerModel, checkRecipeViewModel, recommendationViewModel, randomRecipeDataAccessObject, returnToMainViewModel, loggedInViewModel);
        views.add(recommendView, recommendView.viewName);
        FavouriteRecipesView favouriteRecipesView = CheckFavourRecipeUseCaseFactory.create(viewManagerModel, checkFavourRecipeViewModel, apiRecipeDataAccessObject, loadRecipesViewModel, returnToMainViewModel, removeRecipeViewModel, loggedInViewModel, userDataAccessObject);
        views.add(favouriteRecipesView, favouriteRecipesView.viewName);
//        RecipeView recipeView = SaveFavouriteRecipeUseCaseFactory.create()


        // Set the beginning View(should change to log in)
        viewManagerModel.setActiveView(searchView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}

