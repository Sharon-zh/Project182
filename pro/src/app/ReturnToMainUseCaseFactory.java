package app;


import interface_adapter.ViewManagerModel;
import service.logged_in.interface_adapter.LoggedInViewModel;
import service.return_to_main.interface_adapter.ReturnToMainController;
import service.return_to_main.interface_adapter.ReturnToMainPresenter;
import service.return_to_main.interface_adapter.ReturnToMainViewModel;
import service.return_to_main.use_case.ReturnToMainInputBoundary;
import service.return_to_main.use_case.ReturnToMainInteractor;
import service.return_to_main.use_case.ReturnToMainOutputBoundary;

public class ReturnToMainUseCaseFactory {
    private ReturnToMainUseCaseFactory() {}
    public static ReturnToMainController createReturnToMainUseCase(ReturnToMainViewModel returnToMainViewModel, LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel){
        ReturnToMainOutputBoundary returnToMainOutputBoundary = new ReturnToMainPresenter(returnToMainViewModel, loggedInViewModel, viewManagerModel);
        ReturnToMainInputBoundary returnToMainInteractor = new ReturnToMainInteractor(returnToMainOutputBoundary);
        return new ReturnToMainController(returnToMainInteractor);
    }
}
