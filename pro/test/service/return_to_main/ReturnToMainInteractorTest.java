package service.return_to_main;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import service.login.use_case.*;
import service.return_to_main.use_case.ReturnToMainInputBoundary;
import service.return_to_main.use_case.ReturnToMainInputData;
import service.return_to_main.use_case.ReturnToMainInteractor;
import service.return_to_main.use_case.ReturnToMainOutputBoundary;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReturnToMainInteractorTest {
    @Test
    void successTest() throws IOException {
        ReturnToMainInputData inputData = new ReturnToMainInputData();
        ReturnToMainOutputBoundary returnToMainOutputBoundary = new ReturnToMainOutputBoundary() {
            @Override
            public void prepareSuccessView() {
            }
        };
        ReturnToMainInputBoundary interactor = new ReturnToMainInteractor(returnToMainOutputBoundary);
        interactor.execute();

        // This creates a successPresenter that tests whether the test case is as we expect.

    }
}
