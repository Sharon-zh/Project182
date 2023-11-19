package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

class CommonUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;
    private final ArrayList<String> favoriteRecipes;

    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    public CommonUser(String name, String password, LocalDateTime creationTime) {
        ArrayList<String> favoriteRecipes = new ArrayList<String>();
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
    public ArrayList<String> getFavoriteRecipes(){return favoriteRecipes;}
    public void setFavoriteRecipes(String recipeName){this.favoriteRecipes.add(recipeName);}

}
