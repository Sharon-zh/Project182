package app;

import interface_adapter.ViewManagerModel;
import service.comment.interface_adapter.CommentController;
import service.comment.interface_adapter.CommentPresenter;
import service.comment.interface_adapter.CommentViewModel;
import service.comment.use_case.CommentDataAccessInterface;
import service.comment.use_case.CommentInputBoundary;
import service.comment.use_case.CommentInteractor;
import service.comment.use_case.CommentOutputBoundary;

import java.io.IOException;

public class CommentUseCaseFactory {
    private CommentUseCaseFactory() {}
    public static CommentController createCommentUseCase(ViewManagerModel viewManagerModel, CommentViewModel commentViewModel, CommentDataAccessInterface commentDataAccessObject) throws IOException{
        CommentOutputBoundary commentOutputBoundary = new CommentPresenter(commentViewModel);
        CommentInputBoundary commentInteractor = new CommentInteractor(commentDataAccessObject, commentOutputBoundary);
        return new CommentController(commentInteractor);
    }

}
