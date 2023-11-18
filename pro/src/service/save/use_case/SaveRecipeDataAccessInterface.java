package service.save.use_case;

import entity.User;

public interface SaveRecipeDataAccessInterface {
    void saveRecipe(String userName, String recipeName);
}
