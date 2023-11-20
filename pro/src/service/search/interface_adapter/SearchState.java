package service.search.interface_adapter;

import java.util.Map;

public class SearchState {
    private String searchWord = "";
    private Map result = null;
    private String noResultError = "";

    public SearchState (SearchState copy) {
        searchWord = copy.searchWord;
        result = copy.result;
        noResultError = copy.noResultError;
    }

    public SearchState() {}

    public String getSearchWord() {return searchWord;}

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public Map getResult() {return result;}
    public void setNoResultError(String noResultError) {
        this.noResultError = noResultError;}
}
