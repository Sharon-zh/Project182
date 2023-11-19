package service.delete.interface_adapter;

import service.delete.use_case.DeleteOutputBoundary;
import service.delete.use_case.DeleteOutputData;
import service.logged_in.interface_adapter.LoggedInState;
import service.logged_in.interface_adapter.LoggedInViewModel;

public class DeletePresenter implements DeleteOutputBoundary {
    private final DeleteViewModel deleteViewModel;
    private final LoggedInViewModel loggedInViewModel;


    public DeletePresenter(DeleteViewModel deleteViewModel, LoggedInViewModel loggedInViewModel) {
        this.deleteViewModel = deleteViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }


    @Override
    public void prepareSuccessView(DeleteOutputData deleteOutputData) {
        DeleteState deleteState = deleteViewModel.getState();
        String successMessage = "Delete " + deleteOutputData.getRecipeName() + " successfully!";
        deleteState.setSuccessMessage(successMessage);
        deleteViewModel.firePropertyChanged();

        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.removeFromFavoriteRecipes(deleteOutputData.getRecipeName());
        loggedInViewModel.firePropertyChanged();
    }
}
