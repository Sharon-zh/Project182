package service.comment.use_case;

import java.util.ArrayList;

public interface CommentDataAccessInterface {
    void save(String recipeName, String comment);

    ArrayList<String> getComments(String recipeName);
}
