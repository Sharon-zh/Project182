package app;

import interface_adapter.ViewManagerModel;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.load_favourite_recipes.interface_adapter.LoadRecipesController;
import service.load_favourite_recipes.interface_adapter.LoadRecipesViewModel;
import service.load_favourite_recipes.use_case.LoadRecipesDataAccessInterface;
import service.logged_in.interface_adapter.LoggedInViewModel;
import service.logout.interface_adapter.LogoutController;
import service.logout.interface_adapter.LogoutViewModel;
import service.recommendation.interface_adapter.RecommendationController;
import service.recommendation.interface_adapter.RecommendationViewModel;
import service.recommendation.use_case.RecommendationDataAccessInterface;
import service.search.interface_adapter.SearchController;
import service.search.interface_adapter.SearchPresenter;
import service.search.interface_adapter.SearchViewModel;
import service.search.use_case.SearchDataAccessInterface;
import service.search.use_case.SearchInputBoundary;
import service.search.use_case.SearchInteractor;
import service.search.use_case.SearchOutputBoundary;
import view.SearchView;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {
    private SearchUseCaseFactory() {}

    public static SearchView create(
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            CheckRecipeViewModel checkRecipeViewModel,
            SearchDataAccessInterface userDataAccessObject,
            LogoutViewModel logoutViewModel,
            RecommendationViewModel recommendationViewModel,
            LoadRecipesViewModel loadRecipesViewModel,
            LoggedInViewModel loggedInViewModel,
            LoadRecipesDataAccessInterface loadRecipesDataAccessObject,
            RecommendationDataAccessInterface recommendationDataAccessObject) {
        try {
            SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel,
                    checkRecipeViewModel, userDataAccessObject);
            LogoutController logoutController
            RecommendationController recommendationController =
                    RecommendationUseCaseFactory.createUserRecommendationUseCase(viewManagerModel,checkRecipeViewModel,
                            recommendationViewModel, recommendationDataAccessObject);
            LoadRecipesController loadRecipesController = LoadRecipesUseCaseFactory.createLoadRecipesUseCase(
                    viewManagerModel, loadRecipesViewModel, loadRecipesDataAccessObject);
            return new SearchView(searchViewModel, logoutViewModel, recommendationViewModel,  loadRecipesViewModel,
                    loggedInViewModel, searchController, logoutController, recommendationController, loadRecipesController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open, please try again.");
        }

        return null;
    }

    private static SearchController createSearchUseCase(
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            CheckRecipeViewModel checkRecipeViewModel,
            SearchDataAccessInterface userDataAccessObject) throws IOException {

        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(viewManagerModel, searchViewModel,
                checkRecipeViewModel);

        SearchInputBoundary searchInteractor = new SearchInteractor(
                userDataAccessObject, searchOutputBoundary);

        return new SearchController(searchInteractor);
    }
}
