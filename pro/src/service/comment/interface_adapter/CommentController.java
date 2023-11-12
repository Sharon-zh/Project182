package service.comment.interface_adapter;

import service.comment.use_case.CommentInputBoundary;
import service.comment.use_case.CommentInputData;

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
