package comment.use_case;

public class CommentInputData {
    final private String comment;
    final private String username;


    public CommentInputData(String username, String comment) {
        this.comment = comment;
        this.username = username;
    }

    String getComment(){
        return comment;
    }
}
