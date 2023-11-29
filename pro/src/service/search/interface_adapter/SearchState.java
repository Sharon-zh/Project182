package service.search.interface_adapter;

import entity.Recipe;

import java.util.Map;

public class SearchState {
    private String searchWord = "";
    private Map<String, Recipe> result;
    private String noResultError = null;

    public SearchState (SearchState copy) {
        searchWord = copy.searchWord;
        result = copy.result;
        noResultError = copy.noResultError;
    }

    public SearchState() {}

    public String getSearchWord() {return searchWord;}
    public String getNoResultError() {return noResultError;}

    public void setSearchWord(String searchWord) {this.searchWord = searchWord;}

    public Map<String, Recipe> getSearchResult() {return result;}
    public void setSearchResult(Map<String, Recipe> result) {this.result = result;}

    public void setNoResultError(String noResultError) {this.noResultError = noResultError;}
}
