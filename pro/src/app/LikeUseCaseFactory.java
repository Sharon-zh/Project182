package app;

import service.like.interface_adapter.LikeController;
import service.like.interface_adapter.LikePresenter;
import service.like.interface_adapter.LikeViewModel;
import service.like.use_case.LikeDataAccessInterface;
import service.like.use_case.LikeInputBoundary;
import service.like.use_case.LikeInteractor;
import service.like.use_case.LikeOutputBoundary;

import java.io.IOException;

public class LikeUseCaseFactory {
    private LikeUseCaseFactory() {}

    private static LikeController createLikeUseCase(
            LikeViewModel likeViewModel,
            LikeDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LikeOutputBoundary likeOutputBoundary = new LikePresenter(likeViewModel);
        LikeInputBoundary likeInteractor = new LikeInteractor(
                userDataAccessObject, likeOutputBoundary);

        return new LikeController(likeInteractor);
    }
}
