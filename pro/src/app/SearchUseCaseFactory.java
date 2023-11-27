package app;



import entity.CommonRecipeFactory;
import entity.RecipeFactory;
import interface_adapter.ViewManagerModel;
import service.after_search.interface_adapter.AfterSearchViewModel;
import service.search.interface_adapter.SearchController;
import service.search.interface_adapter.SearchPresenter;
import service.search.interface_adapter.SearchViewModel;
import service.search.use_case.SearchDataAccessInterface;
import service.search.use_case.SearchInputBoundary;
import service.search.use_case.SearchInteractor;
import service.search.use_case.SearchOutputBoundary;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {
    private SearchUseCaseFactory() {}

    public static SearchView create(
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            AfterSearchViewModel afterSearchViewModel,
            SearchDataAccessInterface userDataAccessObject) {

        try {
            SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, afterSearchViewModel, userDataAccessObject);
            //return new SearchView(searchViewModel, searchController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open, please try again.");
        }

        return null;
    }

    private static SearchController createSearchUseCase(
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            AfterSearchViewModel afterSearchViewModel,
            SearchDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(viewManagerModel, afterSearchViewModel, searchViewModel);

        //RecipeFactory recipeFactory = new CommonRecipeFactory();

        SearchInputBoundary searchInteractor = new SearchInteractor(
                userDataAccessObject, searchOutputBoundary);

        return new SearchController(searchInteractor);
    }
}
