package service.load_favourite_recipes.use_case;

import java.util.ArrayList;

public class LoadRecipesOutputData {
    private ArrayList<String> favouriteRecipes;
    private boolean useCaseFailed;

    public LoadRecipesOutputData(ArrayList<String> favouriteRecipes, boolean useCaseFailed) {
        this.favouriteRecipes = favouriteRecipes;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<String> getFavouriteRecipes() {
        return favouriteRecipes;
    }

}
