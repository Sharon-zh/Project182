package service.login;

import data_access.*;
import entity.*;
import org.junit.jupiter.api.Test;
import service.login.use_case.*;
import service.search.use_case.*;
import service.signup.use_case.SignupUserDataAccessInterface;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class LoginInteractorTest {
    @Test
    void successTest() throws IOException {
        LoginInputData inputData = new LoginInputData("zth", "123");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("zth", "123", LocalDateTime.now());
        userRepository.save(user);
        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("zth", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userRepository, loginOutputBoundary);
        interactor.execute(inputData);

        // This creates a successPresenter that tests whether the test case is as we expect.

    }
    @Test
    void failureAccountTest() throws IOException {
        LoginInputData inputData = new LoginInputData("zth", "123");
        UserFactory userFactory = new CommonUserFactory();
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("zth: Account does not exist.", error);
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userRepository, loginOutputBoundary);
        interactor.execute(inputData);

        // This creates a successPresenter that tests whether the test case is as we expect.

    }
    @Test
    void failurePasswordTest() throws IOException {
        LoginInputData inputData = new LoginInputData("zth", "111");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("zth", "123", LocalDateTime.now());
        userRepository.save(user);
        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for zth.", error);
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userRepository, loginOutputBoundary);
        interactor.execute(inputData);

        // This creates a successPresenter that tests whether the test case is as we expect.

    }
}
