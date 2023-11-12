package interface_adapter.comment;

import use_case.comment.CommentInputBoundary;
import use_case.comment.CommentInputData;

public class CommentController {
    final CommentInputBoundary commentInputInteractor;

    public CommentController(CommentInputBoundary commentInputInteractor){
        this.commentInputInteractor = commentInputInteractor;
    }

    public void execute(String username, String comment){
        CommentInputData comment_inputData = new CommentInputData(username, comment);
        commentInputInteractor.excute(comment_inputData);
    }

}
