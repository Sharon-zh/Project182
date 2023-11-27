package service.check_favourite_recipe;

import data_access.ApiRecipeDataAccessObject;
import data_access.InmemoryRecipeDataAccessObject;
import data_access.LikeFileRecipeDateAccessObject;
import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;
import org.junit.jupiter.api.Test;
import service.check_favourite_recipe.use_case.*;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CheckFavourRecipeInteractorTest {
    @Test
    public void successTest() throws IOException {
        CheckFavourRecipeInputData inputData = new CheckFavourRecipeInputData("Beetroot Soup");
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        CheckFavourRecipeDataAccessInterface userRepository = new ApiRecipeDataAccessObject(recipeFactory,
                new InmemoryRecipeDataAccessObject(), new LikeFileRecipeDateAccessObject("xxxx"));
        Map<String, Recipe> recipes = userRepository.searchResult("Beetroot Soup");
        Recipe recipe = recipes.get(recipes.keySet().toArray()[0]);
        CheckFavourRecipeOutputBoundary successPresenter = new CheckFavourRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckFavourRecipeOutputData output) {
                assertEquals(recipe, output.getRecipe());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        CheckFavourRecipeInputBoundary interactor = new CheckFavourRecipeInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }



    @Test
    public void failureTest() throws IOException {
        CheckFavourRecipeInputData inputData = new CheckFavourRecipeInputData("non-existing recipe");
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        CheckFavourRecipeDataAccessInterface userRepository = new ApiRecipeDataAccessObject(recipeFactory,
                    new InmemoryRecipeDataAccessObject(), new LikeFileRecipeDateAccessObject("xxxx"));
        CheckFavourRecipeOutputBoundary failurePresenter = new CheckFavourRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckFavourRecipeOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("The recipe dose not exist.", error);
            }
        };

        CheckFavourRecipeInputBoundary interactor = new CheckFavourRecipeInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}
