package service.delete;

import data_access.InMemoryUserDataAccessObject;
import org.junit.Before;
import org.junit.Test;
import service.logged_in.interface_adapter.LoggedInViewModel;
import service.delete.use_case.*;
import service.save.interface_adapter.SavePresenter;
import service.save.interface_adapter.SaveViewModel;
import service.save.use_case.*;

import static org.junit.Assert.*;

public class DeleteInteractorTest {
    private InMemoryUserDataAccessObject userRepository;
    @Before
    public void saveRecipe() {
        // need to add a user.

        SaveInputData inputData = new SaveInputData("Lisa", "Fish Stew with Rouille");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        SaveViewModel saveViewModel = new SaveViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SaveOutputBoundary successPresenter = new SavePresenter(saveViewModel, loggedInViewModel);
        SaveInputBoundary interactor = new SaveInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
    @Test
    public void deleteSuccessTest() {
        DeleteInputData inputData = new DeleteInputData("Lisa", "Fish Stew with Rouille");

        DeleteOutputBoundary successPresenter = new DeleteOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteOutputData output) {
                assertEquals("Fish Stew with Rouille", output.getRecipeName());
                assertFalse(userRepository.loadFavouriteRecipes("Lisa").contains("Fish Stew with Rouille"));
            }
        };

        DeleteInputBoundary interactor = new DeleteInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
