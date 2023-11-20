package service.load_favourite_recipes.interface_adapter;

import service.load_favourite_recipes.use_case.LoadRecipesInputBoundary;
import service.load_favourite_recipes.use_case.LoadRecipesInputData;

public class LoadRecipesController {
    final LoadRecipesInputBoundary loadRecipesUseCaseInteractor;
    public LoadRecipesController(LoadRecipesInputBoundary loadRecipesUseCaseInteractor) {
        this.loadRecipesUseCaseInteractor = loadRecipesUseCaseInteractor;
    }

    public void execute(String userName) {
        LoadRecipesInputData loadRecipesInputData = new LoadRecipesInputData(userName);
        loadRecipesUseCaseInteractor.execute(loadRecipesInputData);
    }
}
