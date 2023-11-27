package service.check_recipe.use_case;

import entity.Recipe;

public class CheckRecipeOutputData {

    final private Recipe recipe;

    public CheckRecipeOutputData(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

}
