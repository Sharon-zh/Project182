package service.search.use_case;

import java.util.Map;

public interface SearchDataAccessInterface {
    Map searchResult(String searchWord);
    boolean hasResult(Map result);
}
