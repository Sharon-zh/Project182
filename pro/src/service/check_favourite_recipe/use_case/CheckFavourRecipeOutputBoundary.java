package service.check_favourite_recipe.use_case;

public interface CheckFavourRecipeOutputBoundary {
    void prepareSuccessView(CheckFavourRecipeOutputData checkFavourRecipeOutputData);
    void prepareFailView(String error);

}