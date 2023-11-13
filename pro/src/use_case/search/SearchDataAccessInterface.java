package use_case.search;

import java.util.Map;

public interface SearchDataAccessInterface {
    Map searchResult(String searchWord);
    boolean hasResult(Map result);
}
