package service.check_recipe.interface_adapter;

import entity.Recipe;

public class CheckRecipeState {
    private Recipe recipe;

    public CheckRecipeState(CheckRecipeState copy) {
        recipe = copy.recipe;

    }

    public CheckRecipeState() {
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

}
