package service.remove_favourite_recipe;

import data_access.InMemoryUserDataAccessObject;
import org.junit.Before;
import org.junit.Test;
import service.logged_in.interface_adapter.LoggedInViewModel;
import service.remove_favourite_recipe.use_case.*;
import service.save_favourite_recipe.interface_adapter.SaveRecipePresenter;
import service.save_favourite_recipe.interface_adapter.SaveRecipeViewModel;
import service.save_favourite_recipe.use_case.*;

import static org.junit.Assert.*;

public class RemoveRecipeInteractorTest {
    private InMemoryUserDataAccessObject userRepository;
    @Before
    public void saveRecipe() {
        // need to add a user.

        SaveRecipeInputData inputData = new SaveRecipeInputData("Lisa", "Fish Stew with Rouille");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        SaveRecipeViewModel saveRecipeViewModel = new SaveRecipeViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SaveRecipeOutputBoundary successPresenter = new SaveRecipePresenter(saveRecipeViewModel, loggedInViewModel);
        SaveRecipeInputBoundary interactor = new SaveRecipeInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
    @Test
    public void deleteSuccessTest() {
        RemoveRecipeInputData inputData = new RemoveRecipeInputData("Lisa", "Fish Stew with Rouille");

        RemoveRecipeOutputBoundary successPresenter = new RemoveRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(RemoveRecipeOutputData output) {
                assertEquals("Fish Stew with Rouille", output.getRecipeName());
                assertFalse(userRepository.loadFavouriteRecipes("Lisa").contains("Fish Stew with Rouille"));
            }
        };

        RemoveRecipeInputBoundary interactor = new RemoveRecipeInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
