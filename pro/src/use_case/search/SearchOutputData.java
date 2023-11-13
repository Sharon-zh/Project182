package use_case.search;

import java.util.HashMap;
import java.util.Map;

public class SearchOutputData {
    private final Map<String, Object> result;

    private boolean resultsAppear;

    public SearchOutputData(Map<String, Object> result, boolean resultsAppear) {
        this.result = result;
        this.resultsAppear = resultsAppear;
    }

    public Map<String, Object> getSearchResult() {
        return result;
    }
}
