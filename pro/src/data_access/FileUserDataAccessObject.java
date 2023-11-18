package data_access;

import service.save.use_case.SaveRecipeDataAccessInterface;

import java.util.ArrayList;

public class FileUserDataAccessObject implements SaveRecipeDataAccessInterface {
    @Override
    public void saveRecipe(String userName, String recipeName) {

    }
    public ArrayList<String>  loadFavouriteRecipes(String userName) {
        return null;

    }
}
