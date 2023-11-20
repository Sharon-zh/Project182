package service.search.use_case;

import entity.Recipe;

import java.util.HashMap;
import java.util.Map;

public class SearchOutputData {
    private final Map<String, Recipe> result;

    private boolean resultsAppear;

    public SearchOutputData(Map<String, Recipe> result, boolean resultsAppear) {
        this.result = result;
        this.resultsAppear = resultsAppear;
    }

    public Map<String, Recipe> getSearchResult() {
        return result;
    }
}