package service.load_favourite_recipes.interface_adapter;

import java.util.ArrayList;

public class LoadRecipesState {
    private ArrayList<String> favouriteRecipes = new ArrayList<String>();

    private String emptyMessage = "";
    public LoadRecipesState(LoadRecipesState copy) {
        favouriteRecipes = copy.favouriteRecipes;
        emptyMessage = copy.emptyMessage;
    }

    public LoadRecipesState() {
    }
    public ArrayList<String> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(ArrayList<String> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }

    public String getEmptyMessage() {
        return emptyMessage;
    }

    public void setEmptyMessage(String emptyMessage) {
        this.emptyMessage = emptyMessage;
    }

}
