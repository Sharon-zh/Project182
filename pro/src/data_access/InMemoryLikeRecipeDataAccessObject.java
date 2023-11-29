package data_access;

import service.like.use_case.LikeDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryLikeRecipeDataAccessObject implements LikeDataAccessInterface {
    private final Map<String, List<String>> like = new HashMap<>();
    @Override
    public void like(String recipe, String username) {
        if (like.keySet().stream().toList().contains(recipe)) {
            if (like.get(recipe).contains(username)){
                like.get(recipe).remove(username);
            } else {
                like.get(recipe).add(username);
            }
        } else {
            List<String> likeList = new ArrayList<>();
            likeList.add(username);
            like.put(recipe, likeList);
        }
    }

    @Override
    public Integer get(String recipe) {
        if (like.keySet().stream().toList().contains(recipe)) {
            return like.get(recipe).size();
        } else {
            return 0;
        }
    }
}
