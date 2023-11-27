package service.comment.use_case;

import entity.Recipe;

public class CommentInputData {
    final private String comment;
    final private String username;

    final private String recipeName;


    public CommentInputData(String recipeName, String username, String comment) {
        this.comment = comment;
        this.username = username;
        this.recipeName = recipeName;
    }

    String getComment(){
        return comment;
    }

    String getUsername(){
        return username;
    }

    String getRecipeName(){
        return recipeName;
    }
}
