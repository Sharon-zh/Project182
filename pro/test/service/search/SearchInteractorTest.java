package service.search;

import data_access.ApiRecipeDataAccessObject;
import data_access.InMemoryRecipeDataAccessObject;
import data_access.LikeFileRecipeDateAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;
import service.search.use_case.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SearchInteractorTest {

    @Test
    void successTest() throws IOException {
        SearchInputData inputData = new SearchInputData("Arrabiata");
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        SearchDataAccessInterface userRepository = new ApiRecipeDataAccessObject(recipeFactory,
                new InMemoryRecipeDataAccessObject(), new LikeFileRecipeDateAccessObject("xxxx"));
        SearchOutputBoundary searchOutputBoundary = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData result) {
                assertNotNull(result.getSearchResult());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        SearchInputBoundary interactor = new SearchInteractor(userRepository, searchOutputBoundary);
        interactor.execute(inputData);

        // This creates a successPresenter that tests whether the test case is as we expect.

    }
    @Test
    void failureTest() throws IOException {
        SearchInputData inputData = new SearchInputData("non-existing recipe");
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        SearchDataAccessInterface userRepository = new ApiRecipeDataAccessObject(recipeFactory,
                new InMemoryRecipeDataAccessObject(), new LikeFileRecipeDateAccessObject("xxxx"));
        SearchOutputBoundary searchOutputBoundary = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData result) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("No results found", error);
            }
        };
        SearchInputBoundary interactor = new SearchInteractor(userRepository, searchOutputBoundary);
        interactor.execute(inputData);

        // This creates a successPresenter that tests whether the test case is as we expect.

    }
    }