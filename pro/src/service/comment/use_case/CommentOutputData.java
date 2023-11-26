package service.comment.use_case;

public class CommentOutputData {
    private final String recipeName;

    private final String comment;

    public CommentOutputData(String recipeName, String comment){
        this.comment = comment;
        this.recipeName = recipeName;
    }

    public String getComment(){
        return comment;
    }

    public String getRecipeName(){
        return recipeName;
    }
}
