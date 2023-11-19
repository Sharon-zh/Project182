package service.save.interface_adapter;

import service.logged_in.interface_adapter.LoggedInState;
import service.logged_in.interface_adapter.LoggedInViewModel;
import service.save.use_case.SaveOutputBoundary;
import service.save.use_case.SaveOutputData;

public class SavePresenter implements SaveOutputBoundary {
    private final SaveViewModel saveViewModel;
    private final LoggedInViewModel loggedInViewModel;


    public SavePresenter(SaveViewModel saveViewModel, LoggedInViewModel loggedInViewModel) {
        this.saveViewModel = saveViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }


    @Override
    public void prepareSuccessView(SaveOutputData saveOutputData) {
        SaveState saveState = saveViewModel.getState();
        String successMessage = "Save " + saveOutputData.getRecipeName() + " successfully!";
        saveState.setSuccessMessage(successMessage);
        saveViewModel.firePropertyChanged();

        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setFavoriteRecipes(saveOutputData.getRecipeName());
        loggedInViewModel.firePropertyChanged();
    }
}
