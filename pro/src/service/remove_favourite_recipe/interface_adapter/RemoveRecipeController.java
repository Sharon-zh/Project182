package service.remove_favourite_recipe.interface_adapter;

import service.remove_favourite_recipe.use_case.RemoveRecipeInputBoundary;
import service.remove_favourite_recipe.use_case.RemoveRecipeInputData;

public class RemoveRecipeController {
    final RemoveRecipeInputBoundary removeRecipeUseCaseInteractor;
    public RemoveRecipeController(RemoveRecipeInputBoundary removeRecipeUseCaseInteractor) {
        this.removeRecipeUseCaseInteractor = removeRecipeUseCaseInteractor;
    }

    public void execute(String userName, String recipeName) {
        RemoveRecipeInputData removeRecipeInputData = new RemoveRecipeInputData(userName, recipeName);

        removeRecipeUseCaseInteractor.execute(removeRecipeInputData);
    }
}
