package service.check_recipe.interface_adapter;

import entity.Recipe;
import service.check_recipe.use_case.CheckRecipeInputBoundary;
import service.check_recipe.use_case.CheckRecipeInputData;

public class CheckRecipeController {

    final CheckRecipeInputBoundary checkRecipeUseCaseInteractor;
    public CheckRecipeController(CheckRecipeInputBoundary checkRecipeUseCaseInteractor) {
        this.checkRecipeUseCaseInteractor = checkRecipeUseCaseInteractor;
    }

    public void execute(Recipe recipe) {
        CheckRecipeInputData checkRecipeInputData = new CheckRecipeInputData(recipe);

        checkRecipeUseCaseInteractor.execute(checkRecipeInputData);
    }
}
