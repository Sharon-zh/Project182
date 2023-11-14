package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

class CommonUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;
    private final ArrayList<Recipe> favoriteRecipes;

    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(String name, String password, LocalDateTime creationTime) {
        ArrayList<Recipe> favoriteRecipes = new ArrayList<Recipe>();
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.favoriteRecipes = favoriteRecipes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
    public ArrayList<Recipe> getFavoriteRecipes(){return favoriteRecipes;}

}
