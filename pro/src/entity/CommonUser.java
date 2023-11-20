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
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.favoriteRecipes = new ArrayList<String>();
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
