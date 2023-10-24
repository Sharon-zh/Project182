package entity;

import java.util.ArrayList;

public interface Account {
    String getUsername();

    String getPassword();

    ArrayList<Recipe> getFavoriteRecipes();

    ArrayList<Recipe> getViewedRecipes();
}
