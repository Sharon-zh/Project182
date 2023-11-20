package service.remove_favourite_recipe.use_case;

public interface RemoveRecipeDataAccessInterface {
    void removeRecipe(String userName, String recipeName);
}
