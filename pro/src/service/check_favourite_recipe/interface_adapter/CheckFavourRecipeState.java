package service.check_favourite_recipe.interface_adapter;

import entity.Recipe;

import java.util.Map;

public class CheckFavourRecipeState {
    private Recipe recipe;
    private String noResultError = "";

    public CheckFavourRecipeState(CheckFavourRecipeState copy) {
        recipe = copy.recipe;
        noResultError = copy.noResultError;
    }

    public CheckFavourRecipeState() {
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public String getNoResultError() {return this.noResultError ;}

    public void setNoResultError(String noResultError) {this.noResultError = noResultError;}

}
