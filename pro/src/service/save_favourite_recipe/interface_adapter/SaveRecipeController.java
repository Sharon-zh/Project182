package service.save_favourite_recipe.interface_adapter;

import service.save_favourite_recipe.use_case.SaveRecipeInputBoundary;
import service.save_favourite_recipe.use_case.SaveRecipeInputData;

public class SaveRecipeController {
    final SaveRecipeInputBoundary saveUseCaseInteractor;
    public SaveRecipeController(SaveRecipeInputBoundary saveUseCaseInteractor) {
        this.saveUseCaseInteractor = saveUseCaseInteractor;
    }

    public void execute(String userName, String recipeName) {
        SaveRecipeInputData saveRecipeInputData = new SaveRecipeInputData(userName, recipeName);

        saveUseCaseInteractor.execute(saveRecipeInputData);
    }
}
