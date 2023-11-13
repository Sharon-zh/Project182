package service.search.use_case;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData result);
    void prepareFailView(String error);
}
