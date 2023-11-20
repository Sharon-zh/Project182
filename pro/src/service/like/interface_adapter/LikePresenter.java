package service.like.interface_adapter;

import service.like.use_case.LikeOutputBoundary;
import service.like.use_case.LikeOutputData;

public class LikePresenter implements LikeOutputBoundary {
    private final LikeViewModel likeViewModel;

    public LikePresenter(LikeViewModel likeViewModel) {
        this.likeViewModel = likeViewModel;
    }

    @Override
    public void prepareSuccessView(LikeOutputData response) {
        LikeState likeState = likeViewModel.getState();
        likeState.set(response.get());
        this.likeViewModel.setState(likeState);
        this.likeViewModel.firePropertyChanged();
    }
}
