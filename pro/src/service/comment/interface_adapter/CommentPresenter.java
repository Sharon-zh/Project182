package service.comment.interface_adapter;

import service.comment.use_case.CommentOutputBoundary;
import service.comment.use_case.CommentOutputData;

public class CommentPresenter implements CommentOutputBoundary {

    private final CommentViewModel commentViewModel;

    public CommentPresenter(CommentViewModel commentViewModel) {
        this.commentViewModel = commentViewModel;
    }
    @Override
    public void prepareSuccessView(CommentOutputData recipe_comment) {
        CommentState commentState = commentViewModel.getState();
        commentState.setRecipeName(recipe_comment.getRecipeName());
        commentState.setComment(recipe_comment.getComment());
        this.commentViewModel.setState(commentState);
        this.commentViewModel.firePropertyChanged();
    }
}
