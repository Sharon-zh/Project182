package service.after_search.interface_adapter;

import entity.Recipe;

import java.util.HashMap;
import java.util.Map;

public class AfterSearchState {
    private String searchWord = "";
    private Map<String, Recipe> result = new HashMap<>();
    public AfterSearchState(AfterSearchState copy) {searchWord = copy.searchWord;}
    public AfterSearchState() {}

    public String getSearchWord() {return searchWord;}
    public void setSearchWord(String searchWord) {this.searchWord = searchWord;}

    public void setSearchResult(Map<String, Recipe> searchResult) {this.result.putAll(searchResult);}
}
