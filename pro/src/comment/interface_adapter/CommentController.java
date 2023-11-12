package comment.interface_adapter;

import comment.use_case.CommentInputBoundary;
import comment.use_case.CommentInputData;

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
