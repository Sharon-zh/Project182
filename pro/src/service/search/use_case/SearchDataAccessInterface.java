package service.search.use_case;

import entity.Recipe;

import java.util.Map;

public interface SearchDataAccessInterface {
    Map<String, Recipe> searchResult(String searchWord);
    boolean hasResult(Map<String, Recipe> result);
}
