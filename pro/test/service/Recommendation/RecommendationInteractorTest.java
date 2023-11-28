package service.Recommendation;

import data_access.InmemoryRecipeDataAccessObject;
import data_access.LikeFileRecipeDateAccessObject;
import data_access.RandomRecipeDataAccessObject;
import entity.CommonRecipeFactory;
import entity.RecipeFactory;
import org.junit.jupiter.api.Test;
import service.recommendation.use_case.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RecommendationInteractorTest {
    @Test
    public void successTest() throws IOException {
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        RecommendationDataAccessInterface userRepository = new RandomRecipeDataAccessObject(recipeFactory,
                new InmemoryRecipeDataAccessObject(), new LikeFileRecipeDateAccessObject("xxxx"));
        RecommendationOutputBoundary successPresenter = new RecommendationOutputBoundary() {
            @Override
            public void prepareSuccessView(RecommendationOutputData output) {
                assertNotNull(output.randomResult());
            }
        };

        RecommendationInputBoundary interactor = new RecommendationInteractor(userRepository, successPresenter);
        interactor.execute();
    }
}
