package service.like.interface_adapter;

import service.like.use_case.LikeInputBoundary;
import service.like.use_case.LikeInputData;

public class LikeController {
    final LikeInputBoundary likeUseCaseInteractor;

    public LikeController(LikeInputBoundary likeUseCaseInteractor) {
        this.likeUseCaseInteractor = likeUseCaseInteractor;
    }

    public void execute(String recipe, String username){
        LikeInputData likeInputData = new LikeInputData(recipe, username);
        likeUseCaseInteractor.execute(likeInputData);
    }
}
