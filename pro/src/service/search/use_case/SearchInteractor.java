package service.search.use_case;

import entity.Recipe;

import java.util.Map;

public class SearchInteractor implements SearchInputBoundary{
    final SearchDataAccessInterface userDataAccessObject;
    final SearchOutputBoundary searchPresenter;

    public SearchInteractor (SearchDataAccessInterface userDataAccessInterface,
                             SearchOutputBoundary searchOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.searchPresenter = searchOutputBoundary;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        String searchWord = searchInputData.getSearchWord();
        Map<String, Recipe> result= this.userDataAccessObject.searchResult(searchWord);
        if (userDataAccessObject.hasResult(result)) {
            SearchOutputData searchOutputData = new SearchOutputData(result, true);
            searchPresenter.prepareSuccessView(searchOutputData);
        }
        else {
            searchPresenter.prepareFailView("No results found");
        }
    }
}
