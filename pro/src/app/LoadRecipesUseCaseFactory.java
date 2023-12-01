package app;

import interface_adapter.ViewManagerModel;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.load_favourite_recipes.interface_adapter.LoadRecipesController;
import service.load_favourite_recipes.interface_adapter.LoadRecipesPresenter;
import service.load_favourite_recipes.interface_adapter.LoadRecipesViewModel;
import service.load_favourite_recipes.use_case.LoadRecipesDataAccessInterface;
import service.load_favourite_recipes.use_case.LoadRecipesInputBoundary;
import service.load_favourite_recipes.use_case.LoadRecipesInteractor;
import service.load_favourite_recipes.use_case.LoadRecipesOutputBoundary;
import service.login.interface_adapter.LoginViewModel;
import service.logout.interface_adapter.LogoutController;
import service.logout.interface_adapter.LogoutViewModel;
import service.recommendation.interface_adapter.RecommendationController;
import service.recommendation.interface_adapter.RecommendationViewModel;
import service.recommendation.use_case.RecommendationDataAccessInterface;
import service.search.interface_adapter.SearchController;
import service.search.interface_adapter.SearchViewModel;
import service.search.use_case.SearchDataAccessInterface;
import view.SearchView;

import javax.swing.*;
import java.io.IOException;


public class LoadRecipesUseCaseFactory {
    private LoadRecipesUseCaseFactory() {}

    public static SearchView create(
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            CheckRecipeViewModel checkRecipeViewModel,
            SearchDataAccessInterface userDataAccessObject,
            LogoutViewModel logoutViewModel,
            RecommendationViewModel recommendationViewModel,
            LoadRecipesViewModel loadRecipesViewModel,
            LoginViewModel loginViewModel,
            LoadRecipesDataAccessInterface loadRecipesDataAccessObject,
            RecommendationDataAccessInterface recommendationDataAccessObject) {
        try {
            SearchController searchController = SearchUseCaseFactory.createSearchUseCase(viewManagerModel, searchViewModel,
                    checkRecipeViewModel, userDataAccessObject);
            LogoutController logoutController = LogoutUseCaseFactory.createLogoutUseCase(viewManagerModel, loginViewModel);
            RecommendationController recommendationController =
                    RecommendationUseCaseFactory.createUserRecommendationUseCase(viewManagerModel,
                            recommendationViewModel, recommendationDataAccessObject);
            LoadRecipesController loadRecipesController = LoadRecipesUseCaseFactory.createLoadRecipesUseCase(
                    viewManagerModel, loadRecipesViewModel, loadRecipesDataAccessObject);
            return new SearchView(searchViewModel, logoutViewModel, recommendationViewModel,  loadRecipesViewModel,
                    loginViewModel, searchController, logoutController, recommendationController, loadRecipesController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open, please try again.");
        }

        return null;
    }

    public static LoadRecipesController createLoadRecipesUseCase(ViewManagerModel viewManagerModel, LoadRecipesViewModel loadRecipesViewModel, LoadRecipesDataAccessInterface loadRecipesDataAccessObject) throws IOException{
        LoadRecipesOutputBoundary loadRecipesOutputBoundary = new LoadRecipesPresenter(loadRecipesViewModel, viewManagerModel);
        LoadRecipesInputBoundary loadRecipesInputBoundary = new LoadRecipesInteractor(loadRecipesDataAccessObject, loadRecipesOutputBoundary);
        return new LoadRecipesController(loadRecipesInputBoundary);
    }
}
