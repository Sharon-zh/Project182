package service.read;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import service.logged_in.interface_adapter.LoggedInViewModel;
import service.read.use_case.*;
import service.save.interface_adapter.SavePresenter;
import service.save.interface_adapter.SaveViewModel;
import service.save.use_case.SaveInputBoundary;
import service.save.use_case.SaveInputData;
import service.save.use_case.SaveInteractor;
import service.save.use_case.SaveOutputBoundary;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ReadInteractorTest {
    private InMemoryUserDataAccessObject userRepository;
    @Before
    public void saveRecipe() {
        // need to add two users.
        SaveInputData inputData = new SaveInputData("Lisa", "Fish Stew with Rouille");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        SaveViewModel saveViewModel = new SaveViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SaveOutputBoundary successPresenter = new SavePresenter(saveViewModel, loggedInViewModel);
        SaveInputBoundary interactor = new SaveInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
    @Test
    public void successTest() {
        ReadInputData inputData = new ReadInputData("Lisa");

        ReadOutputBoundary successPresenter = new ReadOutputBoundary() {
            @Override
            public void prepareSuccessView(ReadOutputData output) {
                ArrayList<String> expectedFR = new ArrayList<String>();
                expectedFR.add("Fish Stew with Rouille");
                assertEquals(output.getFavouriteRecipes(), expectedFR);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        ReadInputBoundary interactor = new ReadInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
    @Test
    public void failureTest() {
        ReadInputData inputData = new ReadInputData("Paul");

        ReadOutputBoundary failurePresenter = new ReadOutputBoundary() {
            @Override
            public void prepareSuccessView(ReadOutputData output) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String emptyMessage) {
                assertEquals("You haven't saved any recipe.", emptyMessage);
            }
        };

        ReadInputBoundary interactor = new ReadInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}
