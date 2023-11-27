package service.comment.use_case;

public class CommentInteractor implements CommentInputBoundary{
    final CommentDataAccessInterface commentDataAccessObject;

    final CommentOutputBoundary commentOutputPresenter;

    public CommentInteractor(CommentDataAccessInterface commentDataAccessObject, CommentOutputBoundary commentOutputPresenter) {
        this.commentDataAccessObject = commentDataAccessObject;
        this.commentOutputPresenter = commentOutputPresenter;
    }


    @Override
    public void excute(CommentInputData commentInputData) {
        String comment = commentInputData.getUsername() + ": " + commentInputData.getComment();
        commentDataAccessObject.save(commentInputData.getRecipeName(), comment);
        CommentOutputData commentOutputData = new CommentOutputData(commentInputData.getRecipeName(), comment);
        commentOutputPresenter.prepareSuccessView(commentOutputData);
    }
}
