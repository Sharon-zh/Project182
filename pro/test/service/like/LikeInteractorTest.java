package service.like;

import data_access.LikeFileRecipeDateAccessObject;
import org.junit.Test;
import service.like.use_case.*;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class LikeInteractorTest {
    // need to add a user.

    @Test
    public void successTest() throws IOException {
        LikeInputData inputData = new LikeInputData("Fish Stew with Rouille", "Lisa");
        LikeFileRecipeDateAccessObject userRepository = new LikeFileRecipeDateAccessObject("xxxx");

        LikeOutputBoundary successPresenter = new LikeOutputBoundary() {
            @Override
            public void prepareSuccessView(LikeOutputData likeOutputData) {
                assertEquals(1, Optional.ofNullable(likeOutputData.get()));
                assertEquals(Optional.ofNullable(userRepository.get("Fish Stew with Rouille")), 1);
            }
        };

        LikeInputBoundary interactor = new LikeInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
