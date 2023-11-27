package service.check_favourite_recipe.use_case;

import entity.Recipe;

import java.util.Map;

public class CheckFavourRecipeInteractor implements CheckFavourRecipeInputBoundary {
    final CheckFavourRecipeDataAccessInterface userDataAccessObject;
    final CheckFavourRecipeOutputBoundary checkFavourRecipePresenter;

    public CheckFavourRecipeInteractor(CheckFavourRecipeDataAccessInterface userDataAccessObject,
                                       CheckFavourRecipeOutputBoundary checkFavourRecipePresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.checkFavourRecipePresenter = checkFavourRecipePresenter;
    }

    @Override
    public void execute(CheckFavourRecipeInputData checkFavourRecipeInputData) {
        String recipeName = checkFavourRecipeInputData.getRecipeName();
        Map<String, Recipe> recipes = this.userDataAccessObject.searchResult(recipeName);
        if (userDataAccessObject.hasResult(recipes)) {
            Recipe recipe = recipes.get(recipes.keySet().toArray()[0]);
            CheckFavourRecipeOutputData checkFavourRecipeOutputData =
                    new CheckFavourRecipeOutputData(recipe);
            checkFavourRecipePresenter.prepareSuccessView(checkFavourRecipeOutputData);
        }
        else {checkFavourRecipePresenter.prepareFailView("The recipe dose not exist.");}
    }
}