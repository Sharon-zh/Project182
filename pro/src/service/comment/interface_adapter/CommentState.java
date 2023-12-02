package service.comment.interface_adapter;

public class CommentState {
    private String comment;

    private String recipeName;

//    public CommentState(CommentState copy){
//        comment = copy.comment;
//        recipeName = copy.recipeName;
//    }

    public CommentState() {

    }

    public String getComment(){
        return comment;
    }

    public String getRecipeName(){
        return recipeName;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public void setRecipeName(String recipeName){
        this.recipeName = recipeName;
    }
}
