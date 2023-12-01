package service.check_recipe.interface_adapter;

import entity.Recipe;

public class CheckRecipeState {
    private Recipe recipe;
    private String username = "";

    public CheckRecipeState(CheckRecipeState copy) {
        recipe = copy.recipe;
        username = copy.username;
    }

    public CheckRecipeState() {
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }
}
