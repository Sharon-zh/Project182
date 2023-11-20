package service.save_favourite_recipe.use_case;

public class SaveRecipeOutputData {
    private String recipeName;
    private boolean useCaseFailed;

    public SaveRecipeOutputData(String recipeName, boolean useCaseFailed) {
        this.recipeName = recipeName;
        this.useCaseFailed = useCaseFailed;
    }

    public String getRecipeName() {
        return recipeName;
    }

}
