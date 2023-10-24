package entity;

import java.util.ArrayList;
import entity.Recipe;


public class CommonAccount implements Account {
    private final String username;

    private final String password;

    private final ArrayList<Recipe> favoriteRecipes;

    private final ArrayList<Recipe> viewedRecipes;

    CommonAccount(String username, String password, ArrayList<Recipe> favoriteRecipes, ArrayList<Recipe> viewedRecipes){
        this.username = username;
        this.password = password;
        this.favoriteRecipes = favoriteRecipes;
        this.viewedRecipes = viewedRecipes;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public ArrayList<Recipe> getFavoriteRecipes(){
        return favoriteRecipes;
    }

    public ArrayList<Recipe> getViewedRecipes(){
        return viewedRecipes;
    }
}
