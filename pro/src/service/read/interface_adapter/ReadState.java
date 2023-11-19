package service.read.interface_adapter;

import java.util.ArrayList;

public class ReadState {
    private ArrayList<String> favouriteRecipes = new ArrayList<String>();

    private String emptyMessage = "";
    public ReadState(ReadState copy) {
        favouriteRecipes = copy.favouriteRecipes;
        emptyMessage = copy.emptyMessage;
    }

    public ReadState() {
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
