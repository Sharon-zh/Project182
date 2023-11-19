package service.save;

import data_access.InMemoryUserDataAccessObject;
import org.junit.Test;
import service.read.use_case.*;
import service.save.use_case.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SaveInteractorTest {
    // need to add a user.

    @Test
    public void successTest() {
        SaveInputData inputData = new SaveInputData("Lisa", "Fish Stew with Rouille");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        SaveOutputBoundary successPresenter = new SaveOutputBoundary() {
            @Override
            public void prepareSuccessView(SaveOutputData output) {
                assertEquals("Fish Stew with Rouille", output.getRecipeName());
                assertTrue(userRepository.loadFavouriteRecipes("Lisa").contains("Fish Stew with Rouille"));
            }
        };

        SaveInputBoundary interactor = new SaveInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
