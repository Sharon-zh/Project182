package service.remove_favourite_recipe;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.Before;
import org.junit.Test;
import service.remove_favourite_recipe.use_case.*;
import service.save_favourite_recipe.interface_adapter.SaveRecipePresenter;
import service.save_favourite_recipe.interface_adapter.SaveRecipeViewModel;
import service.save_favourite_recipe.use_case.*;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class RemoveRecipeInteractorTest {

    @Test
    public void removeSuccessTest() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory= new CommonUserFactory();
        User user = userFactory.create("Lisa", "password", LocalDateTime.now());
        userRepository.save(user);
        userRepository.saveRecipe("Lisa", "Fish Stew with Rouille");

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
