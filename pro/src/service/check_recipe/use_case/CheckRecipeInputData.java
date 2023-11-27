package service.check_recipe.use_case;

import entity.Recipe;

public class CheckRecipeInputData {

    final private Recipe recipe;

    public CheckRecipeInputData(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
