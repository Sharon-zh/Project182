package app;

import data_access.*;
import entity.CommonRecipeFactory;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeViewModel;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.comment.interface_adapter.CommentViewModel;
import service.jump_to_signup.interface_adapter.JumpToSignupViewModel;
import service.like.interface_adapter.LikeViewModel;
import service.load_favourite_recipes.interface_adapter.LoadRecipesViewModel;
import service.logout.interface_adapter.LogoutViewModel;
import service.recommendation.interface_adapter.RecommendationViewModel;
import service.remove_favourite_recipe.interface_adapter.RemoveRecipeViewModel;
import service.return_to_main.interface_adapter.ReturnToMainViewModel;
import service.save_favourite_recipe.interface_adapter.SaveRecipeViewModel;
import service.search.interface_adapter.SearchViewModel;
import view.*;
import service.signup.interface_adapter.SignupViewModel;
import service.login.interface_adapter.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        // The main application window.
        JFrame application = new JFrame("Recipe Project");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        views.setBorder(BorderFactory.createLineBorder(Color.PINK, 10));
        application.add(views);

        JMenuBar menuBar = new JMenuBar();
        JMenu settingMenu = new JMenu("Setting");

        JMenuItem changeColor = new JMenuItem("Change Border Color");
        settingMenu.add(changeColor);
        changeColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeBackgroundColor(views);
            }
        });

        JMenuItem help = new JMenuItem("Help");
        settingMenu.add(help);
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(views, "After log in:\n " +
                        "Search: Search meal by name.\n " +
                        "Recommendation: Lookup a selection of 10 random meals.\n " +
                        "Favourite recipes: Review the saved recipes");
            }
        });

        menuBar.add(settingMenu);
        application.setJMenuBar(menuBar);

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
        SearchViewModel searchViewModel = new SearchViewModel();
        LogoutViewModel logoutViewModel = new LogoutViewModel();
        CheckFavourRecipeViewModel checkFavourRecipeViewModel = new CheckFavourRecipeViewModel();
        RemoveRecipeViewModel removeRecipeViewModel = new RemoveRecipeViewModel();
        LikeViewModel likeViewModel = new LikeViewModel();
        SaveRecipeViewModel saveRecipeViewModel = new SaveRecipeViewModel();
        JumpToSignupViewModel jumpToSignupViewModel = new JumpToSignupViewModel();


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
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, userDataAccessObject, logoutViewModel, searchViewModel, recommendationViewModel, loadRecipesViewModel, jumpToSignupViewModel, signupViewModel);

        views.add(loginView, loginView.viewName);

        SearchView searchView = LoadRecipesUseCaseFactory.create(viewManagerModel, searchViewModel, checkRecipeViewModel, apiRecipeDataAccessObject, logoutViewModel, recommendationViewModel, loadRecipesViewModel, loginViewModel, userDataAccessObject, randomRecipeDataAccessObject, checkFavourRecipeViewModel);
        views.add(searchView, searchView.viewName);
        SearchResultView searchResultView = SearchUseCaseFactory.create(searchViewModel, checkRecipeViewModel, returnToMainViewModel, viewManagerModel, logoutViewModel, apiRecipeDataAccessObject);
        views.add(searchResultView, searchResultView.viewName);
        RecommendView recommendView = RecommendationUseCaseFactory.create(viewManagerModel, checkRecipeViewModel, recommendationViewModel, randomRecipeDataAccessObject, returnToMainViewModel, logoutViewModel);
        views.add(recommendView, recommendView.viewName);
        FavouriteRecipesView favouriteRecipesView = CheckFavourRecipeUseCaseFactory.create(viewManagerModel, checkFavourRecipeViewModel, apiRecipeDataAccessObject, loadRecipesViewModel, returnToMainViewModel, removeRecipeViewModel, loginViewModel, userDataAccessObject,logoutViewModel);
        views.add(favouriteRecipesView, favouriteRecipesView.viewName);
        RecipeView recipeView = SaveFavouriteRecipeUseCaseFactory.create(viewManagerModel, checkRecipeViewModel, checkFavourRecipeViewModel, likeViewModel, likeFileRecipeDateAccessObject, commentViewModel, recipeDataAccessObject, returnToMainViewModel, logoutViewModel, saveRecipeViewModel, userDataAccessObject, removeRecipeViewModel, userDataAccessObject);
        views.add(recipeView, recipeView.viewName);


        // Set the beginning View(should change to log in)
        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

    private static void changeBackgroundColor(JPanel views) {
        Color color = JColorChooser.showDialog(null, "Choose a color", Color.WHITE);
        if (color != null) {
            views.setBorder(BorderFactory.createLineBorder(color, 10));
        }
    }
}

