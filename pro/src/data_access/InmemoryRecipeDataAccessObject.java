package data_access;

import service.comment.use_case.CommentDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InmemoryRecipeDataAccessObject implements CommentDataAccessInterface {
    private final Map<String, ArrayList<String>> comments = new HashMap<>();

    @Override
    public void save(String recipeName, String comment) {
        if (comments.containsKey(recipeName)) {
            comments.get(recipeName).add(comment);
        } else {
            ArrayList<String> a = new ArrayList<String>();
            a.add(comment);
            comments.put(recipeName, a);
        }
    }

    @Override
    public ArrayList<String> getComments(String recipeName) {
        if (comments.containsKey(recipeName)) {
            return comments.get(recipeName);
        } else {
            return new ArrayList<>();
        }
    }
}
