package service.check_favourite_recipe.use_case;

import entity.Recipe;

public class CheckFavourRecipeOutputData {

    final private Recipe recipe;

    public CheckFavourRecipeOutputData(Recipe recipe) {
        this.recipe = recipe;
    }
    public Recipe getRecipe() {
        return recipe;
    }

}
