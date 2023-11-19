package service.read.use_case;

import java.util.ArrayList;

public class ReadOutputData {
    private ArrayList<String> favouriteRecipes;
    private boolean useCaseFailed;

    public ReadOutputData(ArrayList<String> favouriteRecipes, boolean useCaseFailed) {
        this.favouriteRecipes = favouriteRecipes;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<String> getFavouriteRecipes() {
        return favouriteRecipes;
    }

}
