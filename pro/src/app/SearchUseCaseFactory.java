package app;

import interface_adapter.ViewManagerModel;
import service.check_recipe.interface_adapter.CheckRecipeController;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.logout.interface_adapter.LogoutViewModel;
import service.return_to_main.interface_adapter.ReturnToMainController;
import service.return_to_main.interface_adapter.ReturnToMainViewModel;
import service.search.interface_adapter.SearchController;
import service.search.interface_adapter.SearchPresenter;
import service.search.interface_adapter.SearchViewModel;
import service.search.use_case.SearchDataAccessInterface;
import service.search.use_case.SearchInputBoundary;
import service.search.use_case.SearchInteractor;
import service.search.use_case.SearchOutputBoundary;
import view.SearchResultView;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {
    private SearchUseCaseFactory() {}

    public static SearchResultView create(SearchViewModel searchViewModel, CheckRecipeViewModel checkRecipeViewModel, ReturnToMainViewModel returnToMainViewModel, ViewManagerModel viewManagerModel,
                                          LogoutViewModel logoutViewModel, SearchDataAccessInterface searchDataAccessObject){
        try {
            CheckRecipeController checkRecipeController = CheckRecipeUseCaseFactory.createCheckRecipeUseCase(viewManagerModel, checkRecipeViewModel);
            ReturnToMainController returnToMainController = ReturnToMainUseCaseFactory.createReturnToMainUseCase(returnToMainViewModel, logoutViewModel, viewManagerModel);
            SearchController searchController = SearchUseCaseFactory.createSearchUseCase(viewManagerModel, searchViewModel, checkRecipeViewModel, searchDataAccessObject
            );

            return new SearchResultView(searchViewModel, checkRecipeController, returnToMainController, searchController, checkRecipeViewModel, returnToMainViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    public static SearchController createSearchUseCase(
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
