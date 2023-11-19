package service.logged_in.interface_adapter;

import java.util.ArrayList;

public class LoggedInState {
    private String username = "";
    private ArrayList<String> favoriteRecipes = new ArrayList<>();

    public LoggedInState(LoggedInState copy) {

        username = copy.username;
        favoriteRecipes = copy.favoriteRecipes;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public ArrayList<String> getFavoriteRecipes() {
        return favoriteRecipes;
    }
    public void setFavoriteRecipes(String recipeName) {this.favoriteRecipes.add(recipeName);}

    public void removeFromFavoriteRecipes(String recipeName) {
        this.favoriteRecipes.remove(recipeName);
    }
}
