package service.check_recipe;

import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;
import org.junit.jupiter.api.Test;
import service.check_recipe.use_case.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class CheckRecipeInteractorTest {
    @Test
    void successTest() {
        RecipeFactory recipeFactory= new CommonRecipeFactory();
        HashMap<String,String> ingredients = new HashMap<>();
        Recipe recipe = recipeFactory.create("Strawberries Romanoff", "dessert", "...", ingredients, 0,
                null, null,null);

        CheckRecipeInputData inputData = new CheckRecipeInputData(recipe);

        CheckRecipeOutputBoundary successPresenter = new CheckRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckRecipeOutputData output) {
                assertEquals(recipe, output.getRecipe());
            }

        };
        CheckRecipeInputBoundary checkRecipeInteractor = new CheckRecipeInteractor(successPresenter);
        checkRecipeInteractor.execute(inputData);
    }
}
