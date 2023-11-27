package service.remove_favourite_recipe.interface_adapter;

import service.remove_favourite_recipe.use_case.RemoveRecipeOutputBoundary;
import service.remove_favourite_recipe.use_case.RemoveRecipeOutputData;

public class RemoveRecipePresenter implements RemoveRecipeOutputBoundary {
    private final RemoveRecipeViewModel removeRecipeViewModel;


    public RemoveRecipePresenter(RemoveRecipeViewModel removeRecipeViewModel) {
        this.removeRecipeViewModel = removeRecipeViewModel;
    }


    @Override
    public void prepareSuccessView(RemoveRecipeOutputData removeRecipeOutputData) {
        RemoveRecipeState removeRecipeState = removeRecipeViewModel.getState();
        String successMessage = "Delete " + removeRecipeOutputData.getRecipeName() + " successfully!";
        removeRecipeState.setSuccessMessage(successMessage);
        removeRecipeViewModel.setState(removeRecipeState);
        removeRecipeViewModel.firePropertyChanged();
    }
}
