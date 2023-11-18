package service.save;

import data_access.InMemoryUserDataAccessObject;
import org.junit.Test;
import service.save.use_case.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SaveInteractorTest {
    @Test
    public void successTest() {
        SaveInputData inputData = new SaveInputData("Lisa", "Fish Stew with Rouille");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        SaveOutputBoundary successPresenter = new SaveOutputBoundary() {
            @Override
            public void prepareSuccessView(SaveOutputData output) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Fish Stew with Rouille", output.getRecipeName());
                assertTrue(userRepository.loadFavouriteRecipes("Lisa").contains("Fish Stew with Rouille"));
            }
        };

        SaveInputBoundary interactor = new SaveInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
