package service.like.use_case;

public class LikeInteractor implements LikeInputBoundary{
    final LikeDataAccessInterface userDataAccessObject;
    final LikeOutputBoundary likePresenter;

    public LikeInteractor(LikeDataAccessInterface userDataAccessObject, LikeOutputBoundary likePresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.likePresenter = likePresenter;
    }

    @Override
    public void execute(LikeInputData likeInputData) {
        Integer like = userDataAccessObject.get(likeInputData.getRecipe());
        userDataAccessObject.like(likeInputData.getRecipe(), likeInputData.getUsername());
        LikeOutputData likeOutputData = new LikeOutputData(like);
        likePresenter.prepareSuccessView(likeOutputData);
    }
}
