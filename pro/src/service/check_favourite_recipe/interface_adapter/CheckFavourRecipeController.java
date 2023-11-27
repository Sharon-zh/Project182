package service.check_favourite_recipe.interface_adapter;


import service.check_favourite_recipe.use_case.CheckFavourRecipeInputBoundary;
import service.check_favourite_recipe.use_case.CheckFavourRecipeInputData;

public class CheckFavourRecipeController {

    final CheckFavourRecipeInputBoundary checkFavourRecipeUseCaseInteractor;
    public CheckFavourRecipeController(CheckFavourRecipeInputBoundary checkFavourRecipeUseCaseInteractor) {
        this.checkFavourRecipeUseCaseInteractor = checkFavourRecipeUseCaseInteractor;
    }

    public void execute(String recipeName) {
        CheckFavourRecipeInputData checkFavourRecipeInputData = new CheckFavourRecipeInputData(recipeName);

        checkFavourRecipeUseCaseInteractor.execute(checkFavourRecipeInputData);
    }
}
