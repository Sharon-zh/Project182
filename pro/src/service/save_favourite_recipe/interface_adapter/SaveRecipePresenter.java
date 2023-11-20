package service.save_favourite_recipe.interface_adapter;

import service.logged_in.interface_adapter.LoggedInState;
import service.logged_in.interface_adapter.LoggedInViewModel;
import service.save_favourite_recipe.use_case.SaveRecipeOutputBoundary;
import service.save_favourite_recipe.use_case.SaveRecipeOutputData;

public class SaveRecipePresenter implements SaveRecipeOutputBoundary {
    private final SaveRecipeViewModel saveRecipeViewModel;
    private final LoggedInViewModel loggedInViewModel;


    public SaveRecipePresenter(SaveRecipeViewModel saveRecipeViewModel, LoggedInViewModel loggedInViewModel) {
        this.saveRecipeViewModel = saveRecipeViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }


    @Override
    public void prepareSuccessView(SaveRecipeOutputData saveRecipeOutputData) {
        SaveRecipeState saveRecipeState = saveRecipeViewModel.getState();
        String successMessage = "Save " + saveRecipeOutputData.getRecipeName() + " successfully!";
        saveRecipeState.setSuccessMessage(successMessage);
        saveRecipeViewModel.firePropertyChanged();

        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setFavoriteRecipes(saveRecipeOutputData.getRecipeName());
        loggedInViewModel.firePropertyChanged();
    }
}
