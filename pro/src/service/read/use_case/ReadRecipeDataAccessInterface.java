package service.read.use_case;

import java.util.ArrayList;

public interface ReadRecipeDataAccessInterface {
    ArrayList<String> loadFavouriteRecipes(String userName);

}
