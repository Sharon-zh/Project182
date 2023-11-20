package service.save_favourite_recipe.use_case;


public class SaveRecipeInputData {
    final private String userName;
    final private String recipeName;

    public SaveRecipeInputData(String userName, String recipeName) {
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
