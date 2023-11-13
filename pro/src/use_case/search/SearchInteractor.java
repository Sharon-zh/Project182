package use_case.search;

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
        Map result= this.userDataAccessObject.searchResult(searchWord);
        if (userDataAccessObject.hasResult(result)) {
            SearchOutputData searchOutputData = new SearchOutputData(result, true);
            searchPresenter.prepareSuccessView(searchOutputData);
        }
        else {
            searchPresenter.prepareFailView("No results found");
        }
//        String searchWord = searchInputData.getSearchWord();
//        Map result= this.userDataAccessObject.searchResult(searchWord);
//        SearchOutputData searchOutputData = new SearchOutputData(result);
//        searchPresenter.prepareFailView();
    }
}
