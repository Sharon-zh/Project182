package service.load_favourite_recipes.interface_adapter;

import java.util.ArrayList;

public class LoadRecipesState {
    private ArrayList<String> favouriteRecipes = null;
    private String username = "";

    private String emptyMessage = null;
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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

}
