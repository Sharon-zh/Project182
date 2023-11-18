package service.search.use_case;

import java.io.IOException;
import java.util.Map;

public interface SearchDataAccessInterface {
    Map searchResult(String searchWord) throws IOException;
    boolean hasResult(Map result);
}
