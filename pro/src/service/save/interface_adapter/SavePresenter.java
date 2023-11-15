package service.save.interface_adapter;

import service.save.use_case.SaveOutputBoundary;

public class SavePresenter implements SaveOutputBoundary {
    private final SaveViewModel saveViewModel;


    public SavePresenter(SaveViewModel saveViewModel) {
        this.saveViewModel = saveViewModel;
    }


    @Override
    public void prepareSuccessView(String successMessage) {
        SaveState saveState = saveViewModel.getState();
        saveState.setSuccessMessage(successMessage);
        saveViewModel.firePropertyChanged();
    }
}
