package service.remove_favourite_recipe.use_case;


public class RemoveRecipeInputData {
    final private String userName;
    final private String recipeName;

    public RemoveRecipeInputData(String userName, String recipeName) {
        this.userName = userName;
        this.recipeName = recipeName;
    }
    public String getUserName() {
        return userName;
    }
    public String getRecipeName() {
        return recipeName;
    }
}
