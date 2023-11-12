package search.use_case;

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
        SearchOutputData searchOutputData = new SearchOutputData();
    }
}
