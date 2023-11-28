package service.like;

import data_access.InMemoryUserDataAccessObject;
import data_access.LikeFileRecipeDateAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.Test;
import service.like.use_case.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class LikeInteractorTest {

    @Test
    public void successTest() throws IOException {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory= new CommonUserFactory();
        User user = userFactory.create("Lisa", "password", LocalDateTime.now());
        userRepository.save(user);

        LikeFileRecipeDateAccessObject fileRepository = new LikeFileRecipeDateAccessObject("like_num.csv");
        fileRepository.like("Fish Stew with Rouille", "Lisa");

        LikeInputData inputData = new LikeInputData("Fish Stew with Rouille", "Lisa");

        LikeOutputBoundary successPresenter = new LikeOutputBoundary() {
            @Override
            public void prepareSuccessView(LikeOutputData likeOutputData) {
                assertEquals(1, Optional.ofNullable(likeOutputData.get()));
                assertEquals(Optional.ofNullable(fileRepository.get("Fish Stew with Rouille")), 1);
            }
        };

        LikeInputBoundary interactor = new LikeInteractor(fileRepository, successPresenter);
        interactor.execute(inputData);
    }
}
