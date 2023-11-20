package service.like.use_case;

import java.util.List;

public interface LikeDataAccessInterface {
    void like(String recipe, String username);

    Integer get(String recipe);
}
