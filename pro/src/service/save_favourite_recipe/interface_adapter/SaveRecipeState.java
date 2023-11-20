package service.save_favourite_recipe.interface_adapter;

public class SaveRecipeState {
    private String successMessage = "";


    public SaveRecipeState(SaveRecipeState copy) {
        successMessage = copy.successMessage;
    }

    public SaveRecipeState() {}

    public String getSuccessMessage() {
        return successMessage;
    }
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
