package service.save_favourite_recipe.interface_adapter;

import service.save_favourite_recipe.use_case.SaveRecipeOutputBoundary;
import service.save_favourite_recipe.use_case.SaveRecipeOutputData;

public class SaveRecipePresenter implements SaveRecipeOutputBoundary {
    private final SaveRecipeViewModel saveRecipeViewModel;


    public SaveRecipePresenter(SaveRecipeViewModel saveRecipeViewModel) {
        this.saveRecipeViewModel = saveRecipeViewModel;
    }


    @Override
    public void prepareSuccessView(SaveRecipeOutputData saveRecipeOutputData) {
        SaveRecipeState saveRecipeState = saveRecipeViewModel.getState();
        String successMessage = "Save " + saveRecipeOutputData.getRecipeName() + " successfully!";
        saveRecipeState.setSuccessMessage(successMessage);
        saveRecipeViewModel.setState(saveRecipeState);
        saveRecipeViewModel.firePropertyChanged();
    }
}
