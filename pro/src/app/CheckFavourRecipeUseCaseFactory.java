package app;


import interface_adapter.ViewManagerModel;
import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeController;
import service.check_favourite_recipe.interface_adapter.CheckFavourRecipePresenter;
import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeViewModel;
import service.check_favourite_recipe.use_case.CheckFavourRecipeDataAccessInterface;
import service.check_favourite_recipe.use_case.CheckFavourRecipeInputBoundary;
import service.check_favourite_recipe.use_case.CheckFavourRecipeInteractor;
import service.check_favourite_recipe.use_case.CheckFavourRecipeOutputBoundary;

import java.io.IOException;

public class CheckFavourRecipeUseCaseFactory {
    private CheckFavourRecipeUseCaseFactory() {}
    public static CheckFavourRecipeController createCheckFavourRecipeUseCase(ViewManagerModel viewManagerModel,
                                                                             CheckFavourRecipeViewModel checkFavourRecipeViewModel,
                                                                             CheckFavourRecipeDataAccessInterface checkFavourRecipeDataAccessObject)
            throws IOException {
        CheckFavourRecipeOutputBoundary checkFavourRecipeOutputBoundary =
                new CheckFavourRecipePresenter(viewManagerModel, checkFavourRecipeViewModel);
        CheckFavourRecipeInputBoundary checkFavourRecipeInteractor =
                new CheckFavourRecipeInteractor(checkFavourRecipeDataAccessObject, checkFavourRecipeOutputBoundary);
        return new CheckFavourRecipeController(checkFavourRecipeInteractor);
    }

}
