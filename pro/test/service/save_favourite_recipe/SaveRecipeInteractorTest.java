package service.save_favourite_recipe;

import data_access.InMemoryUserDataAccessObject;
import org.junit.Test;
import service.save_favourite_recipe.use_case.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SaveRecipeInteractorTest {
    // need to add a user.

    @Test
    public void successTest() {
        SaveRecipeInputData inputData = new SaveRecipeInputData("Lisa", "Fish Stew with Rouille");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        SaveRecipeOutputBoundary successPresenter = new SaveRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(SaveRecipeOutputData output) {
                assertEquals("Fish Stew with Rouille", output.getRecipeName());
                assertTrue(userRepository.loadFavouriteRecipes("Lisa").contains("Fish Stew with Rouille"));
            }
        };

        SaveRecipeInputBoundary interactor = new SaveRecipeInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
