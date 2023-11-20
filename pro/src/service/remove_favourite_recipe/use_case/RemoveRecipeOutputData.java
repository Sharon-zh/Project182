package service.remove_favourite_recipe.use_case;

public class RemoveRecipeOutputData {
    private String recipeName;
    private boolean useCaseFailed;

    public RemoveRecipeOutputData(String recipeName, boolean useCaseFailed) {
        this.recipeName = recipeName;
        this.useCaseFailed = useCaseFailed;
    }

    public String getRecipeName() {
        return recipeName;
    }

}
