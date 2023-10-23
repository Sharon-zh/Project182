package entity;

import java.util.ArrayList;

public interface account_interface {
    String getUsername();

    String getPassword();

    ArrayList<Recipe> getFavoriteRecipes();

    ArrayList<Recipe> getViewedRecipes();
}
