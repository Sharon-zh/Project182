package app;

import interface_adapter.ViewManagerModel;
import service.check_recipe.interface_adapter.CheckRecipeController;
import service.check_recipe.interface_adapter.CheckRecipePresenter;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.check_recipe.use_case.CheckRecipeInputBoundary;
import service.check_recipe.use_case.CheckRecipeInteractor;
import service.check_recipe.use_case.CheckRecipeOutputBoundary;

public class CheckRecipeUseCaseFactory {
    private CheckRecipeUseCaseFactory() {}

    public static CheckRecipeController createCheckRecipeUseCase(ViewManagerModel viewManagerModel, CheckRecipeViewModel checkRecipeViewModel){
        CheckRecipeOutputBoundary checkRecipeOutputBoundary = new CheckRecipePresenter(viewManagerModel, checkRecipeViewModel);
        CheckRecipeInputBoundary checkRecipeInteractor = new CheckRecipeInteractor(checkRecipeOutputBoundary);
        return new CheckRecipeController(checkRecipeInteractor);
    }
}
