package service.save_favourite_recipe;

import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.Test;
import service.save_favourite_recipe.use_case.*;
import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SaveRecipeInteractorTest {

    @Test
    public void successTest() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory= new CommonUserFactory();
        User user = userFactory.create("Lisa", "password", LocalDateTime.now());
        userRepository.save(user);
        SaveRecipeInputData inputData = new SaveRecipeInputData("Lisa", "Fish Stew with Rouille");

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
