package use_case.search;

public class SearchInputData {

    final private String searchWord;

    public SearchInputData(String searchWord) {
        this.searchWord = searchWord;
    }

    String getSearchWord() {return searchWord;}
}
