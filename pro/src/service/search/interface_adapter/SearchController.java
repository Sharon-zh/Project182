package service.search.interface_adapter;

import service.search.use_case.SearchInputBoundary;
import service.search.use_case.SearchInputData;

public class SearchController {

    final SearchInputBoundary searchUseCaseInteractor;
    public SearchController (SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
    }

    public void execute (String searchWord) {
        SearchInputData searchInputData = new SearchInputData(searchWord);
        searchUseCaseInteractor.execute(searchInputData);
    }
}
