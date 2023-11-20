package service.remove_favourite_recipe.interface_adapter;

import service.remove_favourite_recipe.use_case.RemoveRecipeOutputBoundary;
import service.remove_favourite_recipe.use_case.RemoveRecipeOutputData;
import service.logged_in.interface_adapter.LoggedInState;
import service.logged_in.interface_adapter.LoggedInViewModel;

public class RemoveRecipePresenter implements RemoveRecipeOutputBoundary {
    private final RemoveRecipeViewModel removeRecipeViewModel;
    private final LoggedInViewModel loggedInViewModel;


    public RemoveRecipePresenter(RemoveRecipeViewModel removeRecipeViewModel, LoggedInViewModel loggedInViewModel) {
        this.removeRecipeViewModel = removeRecipeViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }


    @Override
    public void prepareSuccessView(RemoveRecipeOutputData removeRecipeOutputData) {
        RemoveRecipeState removeRecipeState = removeRecipeViewModel.getState();
        String successMessage = "Delete " + removeRecipeOutputData.getRecipeName() + " successfully!";
        removeRecipeState.setSuccessMessage(successMessage);
        removeRecipeViewModel.firePropertyChanged();

        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.removeFromFavoriteRecipes(removeRecipeOutputData.getRecipeName());
        loggedInViewModel.firePropertyChanged();
    }
}
