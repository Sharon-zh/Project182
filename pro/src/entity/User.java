package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface User {

    String getName();

    String getPassword();

    LocalDateTime getCreationTime();

    ArrayList<String> getFavoriteRecipes();
    void setFavoriteRecipes(String recipeName);
    void setFavoriteRecipeList(ArrayList<String> recipeList);
    void removeFavoriteRecipes(String recipeName);
}
