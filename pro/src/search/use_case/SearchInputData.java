package search.use_case;

public class SearchInputData {

    final private String searchWord;

    public SearchInputData(String searchWord) {
        this.searchWord = searchWord;
    }

    String getSearchWord() {return searchWord;}
}
