package service.load_favourite_recipes;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.Before;
import org.junit.Test;
import service.load_favourite_recipes.use_case.*;
import service.save_favourite_recipe.interface_adapter.SaveRecipePresenter;
import service.save_favourite_recipe.interface_adapter.SaveRecipeViewModel;
import service.save_favourite_recipe.use_case.SaveRecipeInputBoundary;
import service.save_favourite_recipe.use_case.SaveRecipeInputData;
import service.save_favourite_recipe.use_case.SaveRecipeInteractor;
import service.save_favourite_recipe.use_case.SaveRecipeOutputBoundary;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LoadRecipesInteractorTest {

    @Test
    public void successTest() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory= new CommonUserFactory();
        User user = userFactory.create("Lisa", "password", LocalDateTime.now());
        userRepository.save(user);
        userRepository.saveRecipe("Lisa", "Fish Stew with Rouille");

        LoadRecipesInputData inputData = new LoadRecipesInputData("Lisa");

        LoadRecipesOutputBoundary successPresenter = new LoadRecipesOutputBoundary() {
            @Override
            public void prepareSuccessView(LoadRecipesOutputData output) {
                ArrayList<String> expectedFR = new ArrayList<String>();
                expectedFR.add("Fish Stew with Rouille");
                assertEquals(expectedFR, output.getFavouriteRecipes());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoadRecipesInputBoundary interactor = new LoadRecipesInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
    @Test
    public void failureTest() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory= new CommonUserFactory();
        User user = userFactory.create("Lisa", "password", LocalDateTime.now());
        userRepository.save(user);

        LoadRecipesInputData inputData = new LoadRecipesInputData("Lisa");

        LoadRecipesOutputBoundary failurePresenter = new LoadRecipesOutputBoundary() {
            @Override
            public void prepareSuccessView(LoadRecipesOutputData output) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String emptyMessage) {
                assertEquals("You haven't saved any recipe.", emptyMessage);
            }
        };

        LoadRecipesInputBoundary interactor = new LoadRecipesInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}
