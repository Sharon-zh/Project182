package service.check_favourite_recipe.use_case;

import entity.Recipe;

public class CheckFavourRecipeInputData {

    private String recipeName;

    public CheckFavourRecipeInputData(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

}
